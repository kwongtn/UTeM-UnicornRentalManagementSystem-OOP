docker run --name=unicornDb --env="MYSQL_ROOT_PASSWORD=password" -e TZ=Asia/Kuala_Lumpur --publish 8080:3306 --detach mysql
docker exec -it unicornDb mysql -uroot -ppassword