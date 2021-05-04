package com.alodiga.hsm.util;

import java.util.Hashtable;
import java.util.Random;

/* Decompiler 76ms, total 242ms, lines 177 */
import java.util.Hashtable;
import java.util.Random;

public class Block implements Cloneable {
   private byte[] data;
   private static final Hashtable power_2 = new Hashtable();
   private static final Hashtable hex_to_bit;
   private static Random hat = new Random();

   public Block() {
      this.data = new byte[8];

      for(int var1 = 0; var1 < 8; ++var1) {
         this.data[var1] = 0;
      }

   }

   public Block(byte[] var1) {
      this.data = new byte[8];
      if (var1.length >= 8) {
         for(int var2 = 0; var2 < 8; ++var2) {
            this.data[var2] = var1[var2];
         }
      }

   }

   public Block(Block var1, byte[] var2) {
      this.data = new byte[8];

      for(int var3 = 0; var3 < var2.length; ++var3) {
         this.putBit(var3, var1.getBit(var2[var3] - 1));
      }

   }

   public Block(String var1) {
      this(Convert.getData(Convert.fromHexToBin(var1)));
   }

   public void forceParity(boolean var1) {
      for(int var2 = 0; var2 < 8; ++var2) {
         boolean var3 = this.getBit(var2 * 8);

         for(int var4 = 1; var4 < 7; ++var4) {
            var3 ^= this.getBit(var2 * 8 + var4);
         }

         this.putBit(var2 * 8 + 7, var3 ^ var1);
      }

   }

   public void generateRandom() {
      for(int var1 = 0; var1 < 64; ++var1) {
         this.putBit(var1, hat.nextBoolean());
      }

   }

   public void rotateLeft() {
      boolean var1 = this.getBit(0);
      boolean var2 = this.getBit(28);

      for(int var3 = 0; var3 < 55; ++var3) {
         this.putBit(var3, this.getBit(var3 + 1));
      }

      this.putBit(27, var1);
      this.putBit(55, var2);
   }

   public void rotateRight() {
      boolean var1 = this.getBit(27);
      boolean var2 = this.getBit(55);

      for(int var3 = 55; var3 > 0; --var3) {
         this.putBit(var3, this.getBit(var3 - 1));
      }

      this.putBit(0, var1);
      this.putBit(28, var2);
   }

   public void transpose(byte[] var1) {
      Block var2 = (Block)this.clone();

      for(int var3 = 0; var3 < var1.length; ++var3) {
         this.putBit(var3, var2.getBit(var1[var3] - 1));
      }

   }

   public void xor(Block var1) {
      for(int var2 = 0; var2 < 64; ++var2) {
         this.putBit(var2, this.getBit(var2) ^ var1.getBit(var2));
      }

   }

   public Object clone() {
      Block var1 = new Block(this.data);
      return var1;
   }

   public boolean getBit(int var1) {
      byte var2 = this.data[var1 / 8];
      byte var3 = ((Integer)power_2.get(new Integer(var1 % 8))).byteValue();
      return (var2 & var3) == var3;
   }

   public void putBit(int var1, boolean var2) {
      byte var3 = ((Integer)power_2.get(new Integer(var1 % 8))).byteValue();
      this.data[var1 / 8] = var2 ? (byte)(this.data[var1 / 8] | var3) : (byte)(this.data[var1 / 8] & 255 - var3);
   }

   public String toString() {
      return Convert.fromBinToHex(Convert.getString(this.data));
   }

   public String toBinaryString() {
      String var1 = Convert.fromBinToHex(Convert.getString(this.data));
      String var2 = "";
      String var3 = "";

      int var4;
      for(var4 = 0; var4 < var1.length() / 2; ++var4) {
         var2 = var2 + hex_to_bit.get(var1.substring(var4, var4 + 1));
      }

      for(var4 = var1.length() / 2; var4 < var1.length(); ++var4) {
         var3 = var3 + hex_to_bit.get(var1.substring(var4, var4 + 1));
      }

      return "\nL: " + var2 + "\nR: " + var3;
   }

   public boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else if (var1 instanceof Block) {
         return var1.toString().equals(this.toString());
      } else {
         return var1 instanceof String ? var1.equals(this.toString()) : false;
      }
   }

   static {
      power_2.put(new Integer(0), new Integer(128));
      power_2.put(new Integer(1), new Integer(64));
      power_2.put(new Integer(2), new Integer(32));
      power_2.put(new Integer(3), new Integer(16));
      power_2.put(new Integer(4), new Integer(8));
      power_2.put(new Integer(5), new Integer(4));
      power_2.put(new Integer(6), new Integer(2));
      power_2.put(new Integer(7), new Integer(1));
      hex_to_bit = new Hashtable();
      hex_to_bit.put("0", "0000");
      hex_to_bit.put("1", "0001");
      hex_to_bit.put("2", "0010");
      hex_to_bit.put("3", "0011");
      hex_to_bit.put("4", "0100");
      hex_to_bit.put("5", "0101");
      hex_to_bit.put("6", "0110");
      hex_to_bit.put("7", "0111");
      hex_to_bit.put("8", "1000");
      hex_to_bit.put("9", "1001");
      hex_to_bit.put("A", "1010");
      hex_to_bit.put("B", "1011");
      hex_to_bit.put("C", "1100");
      hex_to_bit.put("D", "1101");
      hex_to_bit.put("E", "1110");
      hex_to_bit.put("F", "1111");
   }
}