CURRENT_DIR = $(shell pwd)
CURRENT_IP = $(shell hostname -I | awk '{print $$1}')
HOSTNAME = $(shell hostname)
DB_EXEC = docker exec -ti db
SERVER_EXEC = docker exec -ti backend

install-docker:
	sudo apt-get install apt-transport-https ca-certificates curl gnupg lsb-release
	curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
	echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $$(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
	sudo apt-get update
	sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin
	sudo usermod -aG docker $$USER

up:
	sudo docker compose -f ./docker-compose.yml up -d

down:
	sudo docker compose -f ./docker-compose.yml down

shell:
	$(DB_EXEC) bash

restart: down up

build:
	cd ShiftO-Backend && mvn clean install
	docker build -f Dockerfile . -t faiazhalim/shifto-backend:latest && docker push faiazhalim/shifto-backend:latest