# MIPS assemply test program for testing all the required instructions in the MIPS simulator project
# if the simulator works well : $r1 = 0; $r2 = 2; $r11 = 0x8002; 
# $r12 = 2; $r16 = -2; $r21 = 2;
# 
# @Northeastern University
# Computer Architecture for Computer Scientists (EECE 3230)
#

main:	
	
# test 1: check zero register
# $r1 = 0 if all runs correct

	addi $r0,$r0,2			
	addi $r1,$r0,0		    	# $r1 should be 0


# test 2: check logical commands addi, or, and, nor
# $r2 = 2 if all runs correct

	addi $r2,$r0, 0x5555         # $r2 = 0x5555
	addi $r3,$r0, 0xEEED         # $r3 = 0xFFFFEEED
	addi $r4,$r0, 0xCCCC         # $r4 = 0xFFFFCCCC	
	or   $r2,$r4,$r2	     # $r2 OR $r4  = 0xFFFFDDDD
	
	addi $r5,$r0, 0x3333         # $r5 = 0x3333
	and $r2,$r5,$r2		     # $r2 AND $r5 = 0x1111
	
	nor $r2,$r3,$r2              # if runs correct, $r2 should be 2


# test 3: check commands lw, sw, ori, sub 
# $r11 = 2 if all runs correct

# first store two numbers in memory
	addi $r6,$r0, 0x3200    
	ori $r6,$r6, 0x10            # $r6 = 0x00003210
	addi $r7,$r0, 0x00000080     # define a variable in data memery address range 
	sw $r6,$r7, 0	             # mem[$r7+0] = $r6
	addi $r8,$r0, 0x5600    
	ori $r8, $r8, 0x78           # $r8 = 0x5678
	sw $r8,$r7,4		     # mem[$r7+4] = $r8
	
# now retrieve the two numbers and add them
	lw $r9,$r7,0		    	# $r9 = mem[$r7+0]
	lw $r10,$r7,4		    	# $r10 = mem[$r7+4]
	add $r11,$r9,$r10	    	# $r11 = $r9 + $r10
	addi $r12,$r0, 0x886           # $r12 = 0x8886
	sub $r11,$r11, $r12	        #if runs correct, set $r11 = 2
		
		
# test 4: check jumping commands j and jr + command andi
# $r12 = 2 if all runs correct

	andi $r12,$r12, 0x00000000			    
	j h1
	addi $r12,$r12,17			# should not happen!
h1:
	addi $r12,$r12,1			# if j command works correct :  $r12 = 1 
	addi $r13, $r13, h4			# $r13 is pointer for the next jump
	addi $r0, $r0, 0			# avoid data hazard on control hazard
	addi $r0, $r0, 0
	jr   $r13		        	# jump to $r13
	addi $r12,$r12,23			# should not happen!
h4:
	addi $r12,$r12,1			# if runs correct, $r12 should be 2


# test 5: check branching commands beq and bne
# $r16 = -2 if all runs correct

	add $r14,$r0,$r0		
	beq $r14, $r0, h2
	addi $r14,$r14,7		     	# should not happen!
h2:
	addi $r14,$r14,1			# $r14 = 1 now if all well
	addi $r15,$r14,-2			# $r15 = -1
	addi $r16,$r0,-1		        # $r16 = -1
	addi $r0, $r0, 0			# avoid data hazard on control hazard
	addi $r0, $r0, 0	
	bne $r15, $r16, h3
	addi $r15,$r15,-10		    	# should do this!
h3:
	addi $r16,$r15,9		    	# if runs correct, $r16 should be -2

# test 6: check set instructions slt and slti
# $r21 = 2 if all runs correct

	add $r17,$r0,$r0		
	addi $r18,$r0,-10			# $r18 = -10 
	addi $r19,$r0,15		    	# $r19 = 15
	slt $r20,$r18,$r19			# $r20 = 1 if $r18 < $r19 (true)
	add $r17,$r17,$r20			# $r17 = 1 now if all well
	slti $r21,$r18,-5			# $r21 = 1 if $r18 < -5 (true)
	add $r21,$r21,$r17			# if runs correct, $r21 should be 2


# exit when an uknwon command is given
   	hlt 

#END
