#!/bin/bash
IP_COLLECTOR_HOST=192.168.1.3
IP_COLLECTOR_PORT=1024
IP_COLLECTOR_OUT_PORT=1025

common()
{
	sudo apt -y update
	sudo apt -y install ssh
	sudo systemctl start ssh
	sudo apt -y install make git openjdk-11-jdk
	git clone https://github.com/expert975/banco_SBD
	cd banco_SBD
	make deps
}

waitKey()
{
	echo Ready to change networks. Press any key to continue...
	read -n 1 -s
}

patchMakefile()
{
	ip=$(ip addr | grep 'inet ' | cut -f 6 -d' ' | cut -f 1 -d'/' | tail -n 1)
	cat Makefile | awk -F ' = ' -v varName=HOST -v newValue=$ip \
		'{OFS=" = "} {if($1==varName)$2=newValue} {print}' > Makefile.tmp;
	mv Makefile.tmp Makefile
}

worker()
{
	common
	waitKey
	ip=$(ip addr | grep 'inet ' | cut -f 6 -d' ' | cut -f 1 -d'/' | tail -n 1)
	echo My IP is $ip.
	sleep 2
	echo Patching Makefile...
	patchMakefile
	echo Sending IP to worker IP collector...
	echo $ip | nc -q 1 $IP_COLLECTOR_HOST $IP_COLLECTOR_PORT
	echo Running worker...
	sleep 2
	make -j8 worker
}

server()
{
	common
	waitKey
	ip=$(ip addr | grep 'inet ' | cut -f 6 -d' ' | cut -f 1 -d'/' | tail -n 1)
	echo My IP is $ip.
	sleep 2
	echo Patching Makefile...
	patchMakefile
	echo -n | nc -q 1 $IP_COLLECTOR_HOST $IP_COLLECTOR_OUT_PORT > workerIPs
	sleep 1
	echo -n | nc -q 1 $IP_COLLECTOR_HOST $IP_COLLECTOR_OUT_PORT > workerIPs
	echo Patch server worker list and press key to continue
	read -n 1 -s
	make -j8 server
}


client()
{
	common
	waitKey
	echo -n 'Enter server IP: '
	read ip
	cat src/main/java/Client.java \
		| awk -F ' = ' -v varName='	static String SERVER_HOST' \
		-v newValue="\"$ip\";" \
		'{OFS=" = "} {if($1==varName)$2=newValue} {print}' \
		> src/main/java/Client.java.tmp;
	mv src/main/java/Client.java.tmp src/main/java/Client.java;
	make -j8 client
}

case "$1" in
	worker)
		worker
		exit
		;;
	server)
		server
		exit
		;;
	client)
		client
		exit
		;;
	*)
		echo worker, server or client?
		exit
		;;
esac
