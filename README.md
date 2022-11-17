# Lifted Functional Equivalence
CLEVER V2

## Getting Started
### Prerequisites:
1. Clone the repository
   ```shell
   git clone https://github.com/stepwise-alan/spl-tools.git
   cd spl-tools
   ```

2. Install Python 3.10

3. Install the requirements
   ```shell
   pip3 install -r requirements.txt
   ```

## Usage
```shell
sbt "runMain LiftedEquivalenceCheckerV1 OLD_FILE NEW_FILE FUNCTION_NAME"
```
where `OLD_FILE`/`NEW_FILE` is the path to the old/new file, and `FUNCTION_NAME` is the function name.

## Examples
```shell
sbt "runMain LiftedEquivalenceCheckerV1 examples/toy/old.c examples/toy/new.c client"
```