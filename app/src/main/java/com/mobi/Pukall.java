package com.mobi;

import java.io.ByteArrayOutputStream;

class Pukall {
    public static byte[] encrypt(byte[] data, byte[] key){
        return PK(data,key,false,false);
        }
    public static byte[] decrypt(byte[] data, byte[] key){
            return new byte[0];
        }
    private static byte[] PK(byte[]data, byte[] key, boolean dec,boolean debug){
        ByteArrayOutputStream plainTextStream = new ByteArrayOutputStream();

        int sum1 = 0,
            sum2 = 0,
            keyXorVal = 0;

        byte curByte;
        int[] wkey = new int[8];

        for(int i=0; i < 8; i++)
            wkey[i] = (key[i*2] << 8 & 0xFF00) | (key[i*2 +1] & 0xFF) ;

        for(int i=0; i < data.length; i++){
        curByte = data[i];
        if(debug)
            System.out.println("Working on byte: " + i);

        int temp = 0, byteXorVal=0;
        for(int j = 0; j < 8; j++){
            temp ^= wkey[j];
            sum2  = (sum2+j)*20021 + sum1;
            sum1  = (temp*346)      & 0xFFFF;
            sum2  = (sum2+sum1)     & 0xFFFF;
            temp  =  (temp*20021+1) & 0xFFFF;
            byteXorVal ^= temp ^ sum2;
        }

        if(debug)      {
        System.out.println("ByteX0rVal: "  + Integer.toHexString(byteXorVal));
        String www = "";
        for(int kk : wkey)
        {
        String s = Integer.toHexString(kk & 0xFFFF);
        for(int iiii = 0; iiii < s.length() % 4; iiii ++ )
        www += "0";
        www += s;
        }

        System.out.println("hkey: " + www);
        System.out.println("temp: "  + Integer.toHexString(temp));
        System.out.println("sum1: "  + Integer.toHexString(sum1));
        System.out.println("sum2: "  + Integer.toHexString(sum2));
        System.out.println("keyX0rVal: "  + Integer.toHexString(keyXorVal));
        }

        if(!dec)
        keyXorVal = (curByte * 257);
        curByte = (byte) (((curByte ^ (byteXorVal >> 8)) ^ byteXorVal) & 0xFF);
        if(dec)
        keyXorVal = (curByte * 257) * 0xFFFF;

        if(false){
        System.out.println("--------------------------------------------");
        System.out.println("keyX0rVal1: "  + Integer.toHexString(keyXorVal));
        System.out.println("--------------------------------------------");
        }
        for(int j =0; j <8; j++)
        wkey[j] ^= (keyXorVal & 0xFFFF);

        plainTextStream.write(curByte);
        if(debug)      {
        System.out.println("--------------------------------------------");
        System.out.println("ByteX0rVal: "  + Integer.toHexString(byteXorVal));
        String www = "";
        for(int kk : wkey)
        {
        String s = Integer.toHexString(kk & 0xFFFF);
        for(int iiii = 0; iiii < s.length() % 4; iiii ++ )
        www += "0";
        www += s;
        }

        System.out.println("hkey: " + www);
        System.out.println("temp: "  + Integer.toHexString(temp));
        System.out.println("sum1: "  + Integer.toHexString(sum1));
        System.out.println("sum2: "  + Integer.toHexString(sum2));
        System.out.println("keyX0rVal: "  + Integer.toHexString(keyXorVal));
        System.out.println("--------------------------------------------");
        }

        }
        return plainTextStream.toByteArray();
        }

}
