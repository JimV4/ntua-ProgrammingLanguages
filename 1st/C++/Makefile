all: decrypt graphOil min_fill min_fill2

graphOil: graphOil.o
	g++ graphOil.o -o graphOil

graphOil.o: graphOil.cpp
	g++ -Wall -c graphOil.cpp

min_fill: min_fill.o
		g++ min_fill.o -o min_fill

min_fill.o: min_fill.cpp
		g++ -Wall -c min_fill.cpp

min_fill2: min_fill2.o
	g++ min_fill2.o -o min_fill2

min_fill2.o: min_fill2.cpp
	g++ -Wall -c min_fill2.cpp

decrypt: decrypt.o
	g++ decrypt.o -o decrypt

decrypt.o: decrypt.cpp
	g++ -Wall -c decrypt.cpp

clean:
	rm ./*.o decrypt graphOil min_fill min_fill2
