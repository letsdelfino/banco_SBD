SOURCEPATH = src/main/java
PACKAGE = br/com/SBD/banco_SBD
JFLAGS = -sourcepath $(SOURCEPATH)
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	$(SOURCEPATH)/$(PACKAGE)/Cliente.java \
	$(SOURCEPATH)/$(PACKAGE)/Evento.java \
	$(SOURCEPATH)/$(PACKAGE)/Conta.java \
	$(SOURCEPATH)/$(PACKAGE)/ContaDAO.java \
	$(SOURCEPATH)/$(PACKAGE)/ContaDAOImplementacao.java \
	$(SOURCEPATH)/Main.java

default: compile

compile: $(CLASSES:.java=.class)

run: compile
	java -classpath $(SOURCEPATH) Main

clean:
	$(RM) $(CLASSES:.java=.class)
