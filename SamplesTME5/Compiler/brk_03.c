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
  ILP_Object ilptmp14; 
{ 
  ILP_Object ilptmp15; 
ilptmp15 = ILP_Integer2ILP(0); 
ilptmp14 = (i = ilptmp15); 
} 
while ( 1 ) { 
  ILP_Object ilptmp16; 
{ 
  ILP_Object ilptmp17; 
  ILP_Object ilptmp18; 
ilptmp17 = i; 
ilptmp18 = ILP_Integer2ILP(1); 
ilptmp16 = ILP_LessThan(ilptmp17, ilptmp18);
} 
  if ( ILP_isEquivalentToTrue(ilptmp16) ) {
{ 
  ILP_Object ilptmp19; 
{ 
  ILP_Object ilptmp20; 
  ILP_Object ilptmp21; 
ilptmp20 = i; 
ilptmp21 = ILP_Integer2ILP(1); 
ilptmp19 = ILP_LessThan(ilptmp20, ilptmp21);
} 
  if ( ILP_isEquivalentToTrue(ilptmp19 ) ) {

 break; 

  } else {
{ 
  ILP_Object ilptmp22; 
{ 
  ILP_Object ilptmp23; 
  ILP_Object ilptmp24; 
ilptmp23 = i; 
ilptmp24 = ILP_Integer2ILP(1); 
ilptmp22 = ILP_Plus(ilptmp23, ilptmp24);
} 
(void)(i = ilptmp22); 
} 

  }
}

} else { 
    break; 

}
}
ilptmp14 = ILP_FALSE; 
{ 
  ILP_Object ilptmp25; 
ilptmp25 = i; 
ilptmp14 = ILP_print(ilptmp25);
}
return ilptmp14; 
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
