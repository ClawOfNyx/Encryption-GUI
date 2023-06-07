package com.company;

import java.io.*;

public class ToFromEncryption {

    void toEncrypt(String n) {
        try {
            File fileOut = new File("output.txt");
            FileWriter fw = new FileWriter(fileOut);

            String e = "";
            char ch;
            int i, x, y, z;

            int le = n.length();

            for (i = 0; i < le; i++) {
                ch = n.charAt(i);

                if (ch > 64 && ch < 91) {
                    x = 123 - (ch - 64);

                    e += (char) x;
                } else {
                    if (ch > 96 && ch < 123) {
                        x = (ch - 32) + 7;
                        if (x > 90) {
                            x -= 26;
                        }

                        y = (ch - 32) - 5;
                        if (y < 65) {
                            y += 26;
                        }

                        z = (ch - 32) + 3;
                        if (z > 90) {
                            z -= 26;
                        }
                        e += (char) x;
                        e += (char) y;
                        e += (char) z;
                    } else {
                        if (ch > 47 && ch < 53) {
                            x = 39 - (ch - 47);
                            e += (char) x;
                        } else {
                            if (ch > 52 && ch < 58) {
                                x = 65 - (ch - 52);
                                e += (char) x;
                            } else {
                                e += ch;
                            }
                        }
                    }
                }
            }

            fw.write("The encryption:     " + e + System.lineSeparator());
            fw.flush();
            fw.close();
        } catch (Exception er) {
            System.out.println("Error: " + er.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    void toDecrypt(String n) {
        try {
            File fileOut = new File("output.txt");
            FileWriter fw = new FileWriter(fileOut);

            String de = "";
            char ch;
            int i, x;

            int le = n.length();

            for (i = 0; i < le; i++) {
                ch = n.charAt(i);

                if (ch > 96 && ch < 123) {
                    x = 123 + 64 - ch;
                    de += (char) x;
                } else {
                    if (ch > 64 && ch < 91) {
                        x = ch - 7 + 32;
                        if (x < 97) {
                            x += 26;
                        }

                        de += (char) x;
                        i += 2;
                    } else {
                        if (ch > 33 && ch < 39) {
                            x = 39 + 47 - ch;
                            de += (char) x;
                        } else {
                            if (ch > 59 && ch < 65) {
                                x = 65 + 52 - ch;
                                de += (char) x;
                            } else {
                                de += ch;
                            }
                        }
                    }
                }
            }

            fw.write("The decryption:     " + de + System.lineSeparator());
            fw.flush();
            fw.close();
        } catch (Exception er) {
            System.out.println("Error: " + er.getMessage());
        }
    }
}