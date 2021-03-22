all:
	javac *.java

clean:
	rm *.class

rmi:
	rmiregistry

server:	
	java -Djava.server.rmi.codebaseile:// -Djava.security.policy=policy Servidor

client:
	java -Djava.security.policy=policy Client execCli

