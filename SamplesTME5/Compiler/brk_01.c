#include <stdio.h> 
#include <stdlib.h> 
#include "ilp.h" 

/* Global variables */ 
ILP_Object print;
ILP_Object i;

/* Global prototypes */ 

/* Global functions */ 


ILP_Object ilp_program () 
{ 
{ 
  ILP_Object ilptmp1; 
{ 
  ILP_Object ilptmp2; 
ilptmp2 = ILP_Integer2ILP(0); 
ilptmp1 = (i = ilptmp2); 
} 
while ( 1 ) { 
  ILP_Object ilptmp3; 
ilptmp3 = ILP_TRUE; 
  if ( ILP_isEquivalentToTrue(ilptmp3) ) {
{ 
  ILP_Object ilptmp4; 
{ 
  ILP_Object ilptmp5; 
  ILP_Object ilptmp6; 
ilptmp5 = i; 
ilptmp6 = ILP_Integer2ILP(3); 
ilptmp4 = ILP_GreaterThan(ilptmp5, ilptmp6);
} 
  if ( ILP_isEquivalentToTrue(ilptmp4 ) ) {

 break; 

  } else {
{ 
  ILP_Object ilptmp7; 
{ 
  ILP_Object ilptmp8; 
  ILP_Object ilptmp9; 
ilptmp8 = i; 
ilptmp9 = ILP_Integer2ILP(1); 
ilptmp7 = ILP_Plus(ilptmp8, ilptmp9);
} 
(void)(i = ilptmp7); 
} 

  }
}

} else { 
    break; 

}
}
ilptmp1 = ILP_FALSE; 
{ 
  ILP_Object ilptmp10; 
ilptmp10 = i; 
ilptmp1 = ILP_print(ilptmp10);
}
return ilptmp1; 
} 

} 

static ILP_Object ilp_caught_program () {
  struct ILP_catcher* current_catcher = ILP_current_catcher;
  struct ILP_catcher new_catcher;

  if ( 0 == setjmp(new_catcher._jmp_buf) ) {
    ILP_establish_catcher(&new_catcher);
    return ilp_program();
  };
  return ILP_current_exception;
}

int main (int argc, char *argv[]) 
{ 
  ILP_START_GC; 
  ILP_print(ilp_caught_program()); 
  ILP_newline(); 
  return EXIT_SUCCESS; 
} 
