MARIADB_VERSION = 2.5.1

WGET = wget --quiet --show-progress
JC = javac

HOST = expert.chickenkiller.com

JFLAGS = -sourcepath $(SOURCEPATH)

SOURCEPATH = src/main/java
LIBS = lib/*
PACKAGE = br/com/SBD/banco_SBD
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	$(SOURCEPATH)/$(PACKAGE)/OperationType.java \
	$(SOURCEPATH)/$(PACKAGE)/Database.java \
	$(SOURCEPATH)/$(PACKAGE)/Cliente.java \
	$(SOURCEPATH)/$(PACKAGE)/Event.java \
	$(SOURCEPATH)/$(PACKAGE)/Conta.java \
	$(SOURCEPATH)/$(PACKAGE)/ContaDAO.java \
	$(SOURCEPATH)/$(PACKAGE)/ContaDAOImplementacao.java \
	$(SOURCEPATH)/$(PACKAGE)/Cliente.java \
	$(SOURCEPATH)/$(PACKAGE)/ClienteDAO.java \
	$(SOURCEPATH)/$(PACKAGE)/ClienteDAOImplementacao.java \
	$(SOURCEPATH)/$(PACKAGE)/EventDAO.java \
	$(SOURCEPATH)/$(PACKAGE)/EventDAOImpl.java \
	$(SOURCEPATH)/$(PACKAGE)/Bank.java \
	$(SOURCEPATH)/$(PACKAGE)/BankImpl.java \
	$(SOURCEPATH)/Worker.java \
	$(SOURCEPATH)/Server.java \
	$(SOURCEPATH)/Client.java

default: compile

compile: $(CLASSES:.java=.class)

client: compile deps
	java -Djava.rmi.server.hostname=$(HOST) \
	-classpath $(LIBS):$(SOURCEPATH) Client

worker: compile deps
	java -Djava.rmi.server.hostname=$(HOST) \
	-classpath $(LIBS):$(SOURCEPATH) Worker

server: compile deps
	java -Djava.rmi.server.hostname=$(HOST) \
	-classpath $(LIBS):$(SOURCEPATH) Server

clean:
	$(RM) $(CLASSES:.java=.class)

.PHONY: deps
deps: lib lib/mariadb-$(MARIADB_VERSION).jar

lib:
	@mkdir lib

lib/mariadb-$(MARIADB_VERSION).jar:
	@$(WGET) "https://repo1.maven.org/maven2/org/mariadb/jdbc/mariadb-java-\
	client/$(MARIADB_VERSION)/mariadb-java-client-$(MARIADB_VERSION).jar" \
	-O lib/mariadb-$(MARIADB_VERSION).jar
