init_infrastructure:
	docker-compose up -d

stop_infrastructure:
	docker-compose down

logs_database:
	docker logs bankaccount-postgreSQL -f 

logs_broker:
	docker logs broker -f 

logs_schema:
	docker logs schema-registry -f 
