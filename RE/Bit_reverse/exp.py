code = [0x2c,0x2d,0xA4,0x2A,0xA1,0x2A,0x23,0xBD,0x21,0xA4,0x2A,0xAF,0x29,0x99,0x2B,0xA2,0x29,0xA9,0x98,0xAF,0xB4,0xA9,0xAF,0x33,0xBA,0x37,0x32,0xB0,0xB6,0xB2,0x37,0x3A,0xB0,0x26,0xAF,0x13,0x13,0xAF,0x23,0xBA,0xBA,0x37,0x37,0x90,0x90,0x90,0xBE]
flag = ''
for i in code:
	flag += chr((i >> 7) | (((i << 1)&0xff)))
print(flag)


123,105,106,99,96,99,101,138,98,125,71,129,76,120,71,110,73,112,85,112,124,80,79,112,90,134,80,110,75,79,85,66,124,123,76,131,80,110,101,67,77,140
