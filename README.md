# Lifted Functional Equivalence
CLEVER V2

## Getting Started
### Prerequisites:
1. Clone the repository
   ```
   git clone https://github.com/stepwise-alan/spl-tools.git
   cd spl-tools
   ```

2. Install Python 3.10

3. Install the requirements
   ```
   pip3 install -r requirements.txt
   ```

4. Install [Scala](https://www.scala-lang.org/download/)
   ```
   # On Arch based Linux
   sudo pacman -S scala
   ```

5. Install [SBT](https://www.scala-sbt.org/download.html)
   ```
   # On Arch based Linux
   sudo pacman -S sbt
   ```

6. Install the Scala Requirements
   ```
   sbt # in the same directory as build.sbt
   ```

## Usage

### Timeouts
In early usage, giving the wrong file, can clog your system into a non-halting situation. 
It is a good practice to prefix the commands with a ``timeout 2m``

```shell
sbt "runMain LiftedEquivalenceCheckerV1 OLD_FILE NEW_FILE FUNCTION_NAME"
```
**where `OLD_FILE`/`NEW_FILE` is the path to the old/new file, and `FUNCTION_NAME` is the function name.**

## Examples
```shell
sbt "runMain LiftedEquivalenceCheckerV1 examples/toy/old.c examples/toy/new.c client"
```