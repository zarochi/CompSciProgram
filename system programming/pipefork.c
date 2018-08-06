#include <stdio.h>
#include <limits.h>
#include <string.h>
#include <unistd.h>
 
main()
 
{
  int max = 100, i, c1, c2, data;
  int fildes1[2], fildes2[2];
  FILE *fd;
 
  if (c1 = pipe(fildes1)) /* Create a pipe for two child processes */ {
    perror("unable to create the first pipe");
    exit(1);
  }
  if (c1 = pipe(fildes2)) /* Create a pipe for parent and a child
                                                            process */ {
    perror("unable to create the second pipe");
    exit(1);
  }
  if ((c1 = fork()) == -1) /* Create the first child process */ {
    perror("unable to fork first child");
    exit(1);
  }
  else if (c1 == 0) {
    /* This is the first child process */
	 printf("first child pid = %d\n", getpid());
    close (fildes2[0]);
    close (fildes2[1]);
    close (fildes1[1]);
    fd=fopen("c1file", "w");
    for (i=0;i<25;i++)
    {
      read(fildes1[0], (char *)&data, sizeof(int));
      fprintf(fd, "received %d\n", data);
    }
    exit(1);
  }
  else {
    if ((c2 = fork()) == -1) /* Create the second child process */ {
      perror("unable to fork second child");
      exit(1);
    }
    else if (c2 == 0) {
      /* This is the second child process */
      printf("second child pid = %d\n", getpid());
      close (fildes1[0]);
      close (fildes2[1]);
      fd = fopen("c2file", "w");
      for (i = 0; i < 25; i++) {
        read(fildes2[0], (char *)&data, sizeof(int));
		  data+=5;
        fprintf(fd, "received %d\n", data);
        fprintf(fd, "sending %d\n", data);
        write(fildes1[1], (void *)&data, sizeof(int));
      }
      exit (1);
    }
  }
 
  /* This is the parent process */
 
  }
}

