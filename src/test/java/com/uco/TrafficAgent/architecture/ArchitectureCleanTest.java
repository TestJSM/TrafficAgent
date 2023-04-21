package com.uco.TrafficAgent.architecture;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchitectureCleanTest {

    JavaClasses javaClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            .importPackages("com.uco.TrafficAgent");

    @Test
    void domainDependsOnlyJava(){
        classes()
                .that().resideInAPackage("..domain..")
                .should().onlyAccessClassesThat()
                .resideInAnyPackage("java..", "lombok..", "com.uco.TrafficAgent.domain..")
                .check(javaClasses);
    }

    @Test
    void layersChecks(){
        Architectures.layeredArchitecture().consideringAllDependencies()
                .layer("Infrastructure").definedBy("..infrastructure..")
                .layer("Application").definedBy("..application..")
                .layer("Domain").definedBy("..domain..")

                .whereLayer("Infrastructure").mayNotBeAccessedByAnyLayer()
                .whereLayer("Application").mayOnlyBeAccessedByLayers("Infrastructure")
                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Infrastructure", "Application")
                .check(javaClasses);
    }

    @Test
    void contractShouldBeOnlyInterface(){
        classes()
                .that().resideInAnyPackage("..domain.port..")
                .should().beInterfaces().check(javaClasses);
    }

    @Test
    void controllersWithAnnotation(){
        classes()
                .that().resideInAnyPackage("..infrastructure.repository.user.controller..")
                .should().beAnnotatedWith(RestController.class)
                .andShould().bePublic().check(javaClasses);
    }
}
