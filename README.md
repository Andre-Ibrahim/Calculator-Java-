# Calculator-Java




This project has two calculator implementation

The first uses the java.util Stack to compute procedence of the operations

The second implictely uses the the java stack by running a recursive algorithm for the procedence of the operators


to process input of this program there is a file IO input stream set up that reads the statements line by line
and outputs the result to the console


operators available

unary:
  \- , this unary minus sign indicates a negative number
  \! , the factorial sign {factorial method implemented recursively}
Binary Numerical:
  \+ , addition
  \- , negation
  \x , multiplication
  \/ , division
  \^ , power {power method implemented recursively}
Binary Boolean:
  \< , smaller than method
  \> , larger than method
  \<= , smaller or equal method
  \>= , larger or equal method
  
  
  there also a posibilty the use parenthese for the posiblity to change the procedence
  example:
    input: (2 + 3) * 2
    output: 10
  Requirements to use:
    String processing is done using a string tokenizer spliting on blank space meaning:
    1- there should be at least 1 space or tab between distinct numbers and operators
    2- for unary operators there is no space beteen the numebers and the operators example: 2! + -3
    
    
    
  
