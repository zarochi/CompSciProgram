
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h

mov ah, 3Dh     ;open file
lea Dx, filename
mov al, 10b      ;00=read only, 01=write only, 10=read/write, 11=invalid
int 21h
jc error
mov filehandle, ax
mov bp, 0

error:

start:

mov ah, 42h
mov al, 00h
mov bx, filehandle
mov cx, 0
mov dl, filepos
mov dh, 00h
int 21h
jc error

mov ah, 3Fh     ;read file
mov Bx, filehandle
mov cl, sizes[bp]
mov ch, 00h
lea Dx, text
int 21H
JC error2
cmp ax, 0
jne endoffile

error2:
endoffile:

mov bl, sizes[bp]
mov bh, 0
mov text[bx],0
    
lea bx, text
start1:
    cmp [bx],0
    je loop3
    sub [bx],32
    inc bx
    jmp start1
loop3:

mov ah, 42h
mov al, 00h
mov bx, filehandle
mov cx, 0
mov dl, filepos
mov dh, 00h
int 21h
jc error    
    
mov ah, 40h     ;write file
mov Bx, filehandle
mov cl, sizes[bp]
mov ch, 00h
lea Dx, text
int 21H
JC error2
cmp ax, cx
jne error3

error3:

mov ah, filepos
add ah, sizes[bp]
add ah, 2
mov filepos, ah

inc bp
cmp bp, 9
je stop:
jmp start

stop:

mov ah, 3Eh     ;close file
mov Bx, filehandle
int 21h
JC error4

error4:

ret

filename db "C:\lab17.txt",00h
sizes db 5,4,7,6,8,4,7,7,9
filepos db 0
text db 10 dup(0)
counter dw 0
filehandle dw ?

