SHELL := /bin/bash
.SHELLFLAGS = -e -c
.SILENT:
.ONESHELL:

.EXPORT_ALL_VARIABLES:
ROOT_DIR := $(dir $(realpath $(firstword $(MAKEFILE_LIST))))
GRADLE_CMD := $(ROOT_DIR)/gradlew -b "$(ROOT_DIR)/build.gradle.kts"

.DEFAULT_GOAL: help

.PHONY: help
help:
	@echo "Please use 'make <target>' where <target> is one of"
	@grep -E '^\.PHONY: [a-zA-Z_-]+ .*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = "(: |##)"}; {printf "\033[36m%-30s\033[0m %s\n", $$2, $$3}'

.PHONY: clean  ## 🧹 clean all generated files
clean:
	$(GRADLE_CMD) clean

.PHONY: compile ## 🔨 compile the code
compile:
	$(GRADLE_CMD) compileKotlin compileTestKotlin

.PHONY: test ## ✅ test le service
test:
	$(GRADLE_CMD) check -i

.PHONY: package ## 📦 package into a JAR
package:
	$(GRADLE_CMD) assemble

.PHONY: start-app-only ## 🎮 start the application
start-app-only:
	$(GRADLE_CMD) bootRun
