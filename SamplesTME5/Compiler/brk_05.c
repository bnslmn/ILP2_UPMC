#include <stdio.h> 
#include <stdlib.h> 
#include "ilp.h" 

/* Global variables */ 
ILP_Object print;
ILP_Object L1;
ILP_Object x;
ILP_Object i;
ILP_Object j;

/* Global prototypes */ 

/* Global functions */ 


ILP_Object ilp_program () 
{ 
{ 
  ILP_Object ilptmp38; 
{ 
  ILP_Object ilptmp39; 
ilptmp39 = ILP_Integer2ILP(1); 
ilptmp38 = (i = ilptmp39); 
} 
{ 
  ILP_Object ilptmp40; 
ilptmp40 = ILP_Integer2ILP(0); 
ilptmp38 = (j = ilptmp40); 
} 
while ( 1 ) { 
  ILP_Object ilptmp41; 
{ 
  ILP_Object ilptmp42; 
  ILP_Object ilptmp43; 
ilptmp42 = i; 
ilptmp43 = ILP_Integer2ILP(1); 
ilptmp41 = ILP_LessThanOrEqual(ilptmp42, ilptmp43);
} 
  if ( ILP_isEquivalentToTrue(ilptmp41) ) {
{ 
  ILP_Object ilptmp44; 
{ 
  ILP_Object ilptmp45; 
  ILP_Object ilptmp46; 
{ 
  ILP_Object ilptmp47; 
  ILP_Object ilptmp48; 
ilptmp47 = i; 
ilptmp48 = j; 
ilptmp45 = ILP_Plus(ilptmp47, ilptmp48);
} 
ilptmp46 = ILP_Integer2ILP(2); 
ilptmp44 = ILP_LessThanOrEqual(ilptmp45, ilptmp46);
} 
  if ( ILP_isEquivalentToTrue(ilptmp44 ) ) {

 break; 

  } else {
(void)ILP_FALSE; 

  }
}

	L1c:
	continue;
} else { 

	L1b:
    break; 

}
}
ilptmp38 = ILP_FALSE; 
ilptmp38 = L1; 
{ 
  ILP_Object ilptmp49; 
{ 
  ILP_Object ilptmp50; 
  ILP_Object ilptmp51; 
ilptmp50 = i; 
ilptmp51 = j; 
ilptmp49 = ILP_Plus(ilptmp50, ilptmp51);
} 
ilptmp38 = (x = ilptmp49); 
} 
{ 
  ILP_Object ilptmp52; 
ilptmp52 = x; 
ilptmp38 = ILP_print(ilptmp52);
}
return ilptmp38; 
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
