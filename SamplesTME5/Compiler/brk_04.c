#include <stdio.h> 
#include <stdlib.h> 
#include "ilp.h" 

/* Global variables */ 
ILP_Object print;
ILP_Object L1;
ILP_Object i;

/* Global prototypes */ 

/* Global functions */ 


ILP_Object ilp_program () 
{ 
{ 
  ILP_Object ilptmp26; 
{ 
  ILP_Object ilptmp27; 
ilptmp27 = ILP_Integer2ILP(0); 
ilptmp26 = (i = ilptmp27); 
} 
while ( 1 ) { 
  ILP_Object ilptmp28; 
{ 
  ILP_Object ilptmp29; 
  ILP_Object ilptmp30; 
ilptmp29 = i; 
ilptmp30 = ILP_Integer2ILP(1); 
ilptmp28 = ILP_LessThan(ilptmp29, ilptmp30);
} 
  if ( ILP_isEquivalentToTrue(ilptmp28) ) {
{ 
  ILP_Object ilptmp31; 
{ 
  ILP_Object ilptmp32; 
  ILP_Object ilptmp33; 
ilptmp32 = i; 
ilptmp33 = ILP_Integer2ILP(0); 
ilptmp31 = ILP_Equal(ilptmp32, ilptmp33);
} 
  if ( ILP_isEquivalentToTrue(ilptmp31 ) ) {
{ 
  ILP_Object ilptmp34; 
{ 
  ILP_Object ilptmp35; 
  ILP_Object ilptmp36; 
ilptmp35 = i; 
ilptmp36 = ILP_Integer2ILP(1); 
ilptmp34 = ILP_Plus(ilptmp35, ilptmp36);
} 
(void)(i = ilptmp34); 
} 

  } else {

 break; 

  }
}

	L1c:
	continue;
} else { 

	L1b:
    break; 

}
}
ilptmp26 = ILP_FALSE; 
ilptmp26 = L1; 
{ 
  ILP_Object ilptmp37; 
ilptmp37 = i; 
ilptmp26 = ILP_print(ilptmp37);
}
return ilptmp26; 
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
