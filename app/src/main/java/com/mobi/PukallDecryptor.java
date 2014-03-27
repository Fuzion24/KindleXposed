package com.mobi;


import java.io.ByteArrayOutputStream;

public class PukallDecryptor{
    char ax,bx,cx,dx,si,tmp,x1a2,res,i,inter,cfc,cfd,compte;
    byte cle[] = new byte [17];               // holds key
    char x1a0[] = new char [8];
    short c;


    public byte[] decrypt(byte[] key, byte[] data){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for(byte x: data){
            int c = (int) x;

            assemble();

            cfc=(char) (inter>>8);

            cfd=(char) (inter&255); /* cfc^cfd = random byte */

                 /* K ZONE !!!!!!!!!!!!! */
                 /* here the mix of c and cle[compte] is after the decryption of c */

            c = c ^ (cfc^cfd);

            for (compte=0;compte<=15;compte++)
            {
	            /* we mix the plaintext byte with the key */
                cle[compte]=(byte) (cle[compte]^ c);
            }

            baos.write(c);
        }
        return baos.toByteArray();
    }

    private void assemble()
    {

        x1a0[0]= (char) (( cle[0]*256 )+ cle[1]);

        code();
        inter=res;

        x1a0[1]= (char) (x1a0[0] ^ ( (cle[2]*256) + cle[3] ));
        code();
        inter=(char) (inter^res);

        x1a0[2]= (char) (x1a0[1] ^ ( (cle[4]*256) + cle[5] ));
        code();
        inter=(char) (inter^res);

        x1a0[3]= (char) (x1a0[2] ^ ( (cle[6]*256) + cle[7] ));
        code();
        inter=(char) (inter^res);

        x1a0[4]= (char) (x1a0[3] ^ ( (cle[8]*256) + cle[9] ));
        code();
        inter=(char) (inter^res);

        x1a0[5]= (char) (x1a0[4] ^ ( (cle[10]*256) + cle[11] ));
        code();
        inter=(char) (inter^res);

        x1a0[6]= (char) (x1a0[5] ^ ( (cle[12]*256) + cle[13] ));
        code();
        inter=(char) (inter^res);

        x1a0[7]= (char) (  x1a0[6] ^ ( (cle[14]*256) + cle[15] ) );
        code();
        inter=(char) (inter^res);

        i=0;
    }

    void code()
    {
        dx=(char) (x1a2+i);
        ax=x1a0[i];

        cx=0x015a;
        bx=0x4e35;

        tmp=ax;
        ax=si;
        si=tmp;

        tmp=ax;
        ax=dx;
        dx=tmp;

        if (ax!=0)  {
            ax=(char) (ax*bx);
        }

        tmp=ax;
        ax=cx;
        cx=tmp;

        if (ax!=0) {
            ax=(char) (ax*si);
            cx=(char) (ax+cx);
        }

        tmp=ax;
        ax=si;
        si=tmp;
        ax=(char) (ax*bx);
        dx=(char) (cx+dx);

        ax=(char) (ax+1);

        x1a2=dx;
        x1a0[i]=ax;

        res=(char) (ax^dx);
        i=(char) (i+1);
    }


}
