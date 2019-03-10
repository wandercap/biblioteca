package br.com.ps.biblioteca.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class TextFileDao <T> {
	
	@SuppressWarnings("unchecked")
	public ArrayList<T> getLista(String nomeArq) {
        try {
        	File newArq = new File(nomeArq);
        	newArq.createNewFile();
        	FileInputStream arqIn = new FileInputStream(newArq);
        	ObjectInputStream in = new ObjectInputStream(arqIn);
        	ArrayList<T> g = new ArrayList<T>();

            g = (ArrayList<T>)in.readObject();

            in.close();
            
            //System.out.println("Leitura efetuada!");
            return g;
        }
        catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return new ArrayList<T>();
	}
	
	public boolean setLista(ArrayList<T> lista, String nomeArq) {
        try {
        	File newArq = new File(nomeArq);
        	newArq.createNewFile();
        	FileOutputStream arqOut = new FileOutputStream(newArq);
        	ObjectOutputStream out = new ObjectOutputStream(arqOut);
            
            out.writeObject(lista);
            out.flush();
            out.close();
            //System.out.println("Gravacao efetuada!");
            return true;
        } 
        catch(java.io.IOException exc2) {
        	//System.out.println("Erro ao Gravar o arquivo");
        	return false;
        }
	}

}