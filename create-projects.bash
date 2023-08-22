#!/usr/bin/env bash

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=sections-service \
--package-name=com.champlain.sectionsservice \
--groupId=com.champlain.sectionsservice \
--dependencies=webflux \
--version=1.0.0-SNAPSHOT \
sections-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=students-service \
--package-name=com.champlain.studentsservice \
--groupId=com.champlain.studentsservice \
--dependencies=webflux \
--version=1.0.0-SNAPSHOT \
students-service

spring init \
--boot-version=3.0.2 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=courses-service \
--package-name=com.champlain.courseservice \
--groupId=com.champlain.coursesservice \
--dependencies=webflux \
--version=1.0.0-SNAPSHOT \
courses-service


