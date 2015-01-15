package com.boxapp;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class vista03 extends Activity {

	// Declaracion de variables
	EditText correo1;
	EditText correo2;
	EditText contra1;
	EditText contra2;
	EditText cedula;
	
	Button boton1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vista03);
		
		// Asignacion de un valor a la variable del mismo tipo que se declaro 
		correo1 = (EditText)findViewById(R.id.editText1);
		correo2 = (EditText)findViewById(R.id.editText2);
		contra1 = (EditText)findViewById(R.id.editText4);
		contra2 = (EditText)findViewById(R.id.editText5);
		cedula  = (EditText)findViewById(R.id.editText3);
		
		boton1    = (Button) findViewById(R.id.button1);
		
		// Accion al click
		boton1.setOnClickListener(
				
				new View.OnClickListener()
				{
					@Override
					public void onClick(View view){
						 	
						// Declaracion del objeto o clase Alert Dialogo
						//AlertDialog alertDialog = new AlertDialog.Builder(vista03.this).create();

						// Declaracion de variables para ahorra sintaxis y inicializacion
						String c1=correo1.getText().toString();
						String c2=correo2.getText().toString();
						boolean c=false;
						
						if(c1.equals(c2) && !c2.equals("") && !c1.equals("")){
							// logica de acierto
							c=true; 
						}
						
						if(!c1.equals(c2)){
							correo1.setError("Correos Diferentes");
							correo2.setError("Correos Diferentes");
						}	
						
						
						String p1=contra1.getText().toString();
						String p2=contra2.getText().toString();
						boolean p=false;
						
						if(p1.equals(p2) && !p2.equals("") && !p1.equals("")){
							
				            p=true;
				            
						}
						
						if(!p1.equals(p2)){
							//Logica de negado
							contra1.setError("Contrasenas Diferentes");
							contra2.setError("Contrasenas Diferentes");
						}	
						
						boolean id=false;
						
						if(cedula.getText().toString().equals("")){
							//
							cedula.setError("Campo Vacio");	
						}
					
						else{
							id=true;
						}
						
						if(c && p && id){
							
							
						}
						
					}
				}
				
		); //Cierra boton1
	}
}
	