clean_build:
	rm -rf project
	rm -rf target

clean:
	rm 0.c
	rm 0.pi*
	rm 1.c
	rm new.c
	rm old.c

build:
	sbt

run:
	sbt run

run_toy:
	./example_toy.sh