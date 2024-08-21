package com.rra.tracker.controller;

//import net.lingala.zip4j.crypto.AESEncrpyter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TestEnc {
    private static SecretKey secretKey;
    private static String mykey = "67556B58703273357638792F423F4528482B4D6250655368566D597133743677";
    public String AESDecrypt(String strToDecrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt))); //For Base64 input
//            System.out.println("Decrypted Is Working");
            return new String(cipher.doFinal(HexToByteNew(strToDecrypt))); //For Hex input
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public String AESEncrypt(String strToEncrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))); //For Base64 output
            return ByteToHexNew(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));  //For Hex output
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String ByteToHexNew(byte[] publicKey) {
        String outValue = "";
        StringBuffer retString = new StringBuffer();
        try {


            for (int i = 0; i < publicKey.length; ++i) {
                retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
            }
            outValue = retString.toString();
            return outValue;


        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            return outValue;
        }


    }

    public static void setKey() {
        try {
            byte[] raw = HexToByteNew(mykey);

            secretKey = new SecretKeySpec(raw, "AES");
        } catch (Exception e) {
            System.out.println("setKey: " + e.toString());
        }

    }

    public static byte[] HexToByteNew(String keyData) {

        byte[] val = null;
        try {
            val = new byte[keyData.length() / 2];
            for (int i = 0; i < val.length; i++) {
                int index = i * 2;
                int j = Integer.parseInt(keyData.substring(index, index + 2), 16);
                val[i] = (byte) j;
            }

            return val;

        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            return val;
        }

    }

    public static void main(String[] args) {
        TestEnc e = new TestEnc();
        System.out.println("2396302736531921 ~"+e.AESEncrypt("2396302736531921"));
        System.out.println("2541059035629718 ~"+e.AESEncrypt("2541059035629718"));
        System.out.println("9781375821854723 ~"+e.AESEncrypt("9781375821854723"));
        System.out.println("2071821361289609 ~"+e.AESEncrypt("2071821361289609"));
        System.out.println("4854720671457058 ~"+e.AESEncrypt("4854720671457058"));
        System.out.println("4279315097802386 ~"+e.AESEncrypt("4279315097802386"));
        System.out.println("2396302736531921 ~"+e.AESEncrypt("2396302736531921"));
        System.out.println(e.AESDecrypt("2f63ef7b70d5c48c5edea9a161cb7b2a4b4362b70f587dabd42ec96ccb8055ca"));
        //1a7a5e8d0db817b3719c6e9236e86d314b4362b70f587dabd42ec96ccb8055ca
    }
}
