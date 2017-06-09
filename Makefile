SHELL=/bin/bash
VERSION=$(shell echo `git describe --abbrev=0 --tags`)
PROJECT_NAME=ms-cognitive-demo
VERSION_TAG=$(VERSION)
REGISTRY=registry.cn-hangzhou.aliyuncs.com
IMAGE=$(REGISTRY)/lisong/$(PROJECT_NAME)
VERSIONED_IMAGE=$(IMAGE):$(VERSION_TAG)
STATIC_SOURCE=/home/lisong/sources/web_test_base/cognitive
STATIC_DEPLOY=/var/www/html/demos/
CONFIG_DIR=../config
export VERSION
export VERSIONED_IMAGE

clean:
	docker-compose down
	docker-compose rm --force

pull:
	docker pull $(VERSIONED_IMAGE)

build:
	docker build --no-cache --build-arg VERSION=$(VERSION) \
	-t $(VERSIONED_IMAGE) .

up: pull
	docker-compose up -d

static-update:
	cd $(STATIC_SOURCE) && git pull

static:
	sudo cp -rf $(STATIC_SOURCE) $(STATIC_DEPLOY)

package:
	git reset --hard && git pull origin master
	cp -rf $(CONFIG_DIR)/* src/main/resources
	mvn clean package
	cp -f target/$(PROJECT_NAME)-$(VERSION) .
