MARIADB_VERSION = 2.5.1

WGET = wget --quiet --show-progress
JC = javac

JFLAGS = -sourcepath $(SOURCEPATH)

SOURCEPATH = src/main/java
LIBS = lib/*
PACKAGE = br/com/SBD/banco_SBD
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	$(SOURCEPATH)/$(PACKAGE)/Database.java \
	$(SOURCEPATH)/$(PACKAGE)/Cliente.java \
	$(SOURCEPATH)/$(PACKAGE)/Evento.java \
	$(SOURCEPATH)/$(PACKAGE)/Conta.java \
	$(SOURCEPATH)/$(PACKAGE)/ContaDAO.java \
	$(SOURCEPATH)/$(PACKAGE)/ContaDAOImplementacao.java \
	$(SOURCEPATH)/Main.java

default: compile

compile: $(CLASSES:.java=.class)

run: compile deps
	java -classpath $(LIBS):$(SOURCEPATH) Main

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
