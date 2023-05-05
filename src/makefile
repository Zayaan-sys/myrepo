JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<


CLASSES=Edge.class	GenerateRandomGraph.class Graph.class GraphException.class GraphExperiment Path.class Vertex.class writeGraphToFile.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default:$(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
	
run:$(CLASS_FILES)
	java -cp bin Graph
	
