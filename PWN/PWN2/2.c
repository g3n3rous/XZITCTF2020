#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
char bss[0x100];
int main(){
	setvbuf(stdin,0,1,0);
	setvbuf(stdout,0,2,0);
	setvbuf(stderr,0,2,0);
	char array[0x20];
	printf("Just do it\n");
	printf("For bss\n");
	read(0,&bss,0x100);
	printf("To jump bss\n");
	read(0,&array,0x30);
	return 0;
}