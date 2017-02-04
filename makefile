JFLAGS = -g
JC = javac -Xlint:unchecked

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Bigram.java \
	BigramDyn.java \
	BigramMap.java \
	singleBigram.java \
	_mainTester.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
