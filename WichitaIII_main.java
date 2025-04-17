package wichitaIII;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WichitaIII_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in); // Creamos un objeto scanner de la clase Scanner. Pasaremos el objeto
													// scanner a los métodos estáticos en que se necesite desde el
													// método principal
		boolean continuar = true; // Inicializa la variable booleana continuar como true (por defecto, si no se
									// especifica, se inicializa en false)

		File archivo = new File("estudiantes.txt"); // inicializamos la variable de tipo File que generará el archivo de
													// texto "estudiantes.txt"

		while (continuar) { // Se mantiene en el bucle while mientras no se modifique la variable booleana
							// continuar a false

			try {
				System.out.println("Elige una opción");
				System.out.println("1. Añadir Estudiante");
				System.out.println("2. Listar Estudiantes");
				System.out.println("3. Eliminar listado de estudiantes");
				System.out.println("4. Buscar estudiante");
				System.out.println("5. Actualizar edad de estudiante");
				System.out.println("6. Eliminar estudiante");
				System.out.println("7. Salir");
				int opcion = scanner.nextInt(); // Se crea el objeto de tipo entero opcion que tomará el valor
												// introducido
												// por teclado gracias a la clase Scanner

				switch (opcion) {

				case 1:
					scanner.nextLine(); // Limpia el buffer para que no se quede ninguna orden guardada temporalmente y
										// evitar errores en el desarrollo del menú. Se repetirá en todos los case
					boolean add = true; // Se inicializa la variable booleana add
					String apellidos; // Declaramos las variables sin inicializarlas, salvo edad, que sí se inicializa
										// con valor 0 para poder hacer la comparación posterior
					String nombre;
					String edad1;
					int edad = 0;

					while (add) { // Se mantiene en el bucle mientras add = true

						System.out.println("Si ha terminado de añadir estudiantes al listado, escriba: fin");

						final String fin = "fin"; // Inicializamos la variable fin con el valor string "fin" para dar
													// instrucciones posteriormente con las que salir del bucle

						System.out.println("Introduce los apellidos del estudiante");

						apellidos = scanner.nextLine(); // Se introduce la variable apellidos de tipo String por
														// teclado y cambia de línea

						if (apellidos.equalsIgnoreCase(fin)) {
							add = false; // Si apellidos es igual a "fin", obviando si lleva mayúsculas o no, cambia el
											// valor de add a false para salir del bucle

						} else { // Si no cambia add a false, continúa
							System.out.println("Introduce el nombre de pila del estudiante");
							nombre = scanner.nextLine(); // Se introduce la variable nombre de tipo String por
							// teclado y cambia de línea
							if (nombre.equalsIgnoreCase(fin)) {
								add = false; // Mismo procedimiento para salir del bucle
							} else {
								System.out.println("Introduce la edad del estudiante");
								edad1 = scanner.nextLine(); // Se introduce la variable edad1 de tipo string por teclado
															// y cambia de línea

								if (edad1.equalsIgnoreCase(fin)) {
									add = false; // Se ha inicializado edad1 como variable de tipo string para poder
													// hacer la equivalencia con la constante "fin" y que pueda salir
													// del bucle si se da la orden
								} else {
									try {
										edad = Integer.parseInt(edad1); // Se transforma la variable de tipo string
																		// edad1 a la variable edad de tipo int para que
																		// esa sea la indicación de la edad a partir de
																		// ahora
									} catch (NumberFormatException e) { // Si se ha introducido mal edad1 y no puede
																		// transformarse a tipo entero, captura una
																		// excepción y vuelve a iniciar el bucle while
																		// del case1
										System.out.println("Por favor, introduce una edad válida.");

									}

									addEstudiante(archivo, apellidos, nombre, edad); // Lanza el método estático
																						// addEstudiante

								}

							}

						}

					}

					break;
				case 2:
					scanner.nextLine();
					listarEstudiantes(archivo); // Lanza el método estático listarEstudiantes
					break;
				case 3:
					scanner.nextLine();
					eliminarListado(archivo, scanner); // Lanza el método estático eliminarListado
					break;
				case 4:
					scanner.nextLine();
					System.out.println("Introduzca los apellidos del estudiante a buscar");
					String apellidosBuscados = scanner.nextLine(); // Se declara e inicializa la variable de tipo String
																	// apellidosBuscados con el valor que se introduzca
																	// por teclado
					System.out.println("Introduzca el nombre del estudiante a buscar");
					String nombreBuscado = scanner.nextLine(); // Se declara e inicializa la variable de tipo String
																// nombreBuscados con el valor que se introduzca por
																// teclado

					buscarEstudiante(archivo, apellidosBuscados, nombreBuscado); // Lanza el método estático
																					// buscarEstudiante
					break;
				case 5:
					scanner.nextLine();
					System.out.println("Introduzca los apellidos del estudiante a buscar");
					apellidosBuscados = scanner.nextLine(); // Mantenemos las mismas variables que en el caso anterior.
															// Al tomar el valor que se introduce por teclado, pueden
															// utilizarse en métodos diferentes
					System.out.println("Introduzca el nombre del estudiante a buscar");
					nombreBuscado = scanner.nextLine();

					actualizarEdadEstudiante(archivo, apellidosBuscados, nombreBuscado, scanner); // Lanza el método
																									// estático
					// actualizarEdadEstudiante
					break;
				case 6:
					scanner.nextLine();

					System.out.println("Introduzca los apellidos del estudiante a eliminar");
					apellidosBuscados = scanner.nextLine();
					System.out.println("Introduzca el nombre del estudiante a eliminar");
					nombreBuscado = scanner.nextLine();

					eliminarEstudiante(archivo, apellidosBuscados, nombreBuscado, scanner); // Lanza el método estático
					// eliminarEstudiante
					break;
				case 7:
					continuar = false; // Cambia el valor de continuar a false, con lo que sale del bucle
					System.out.println("Operación finalizada");
					break;
				default:
					System.out.println("Opción no válida. Por favor, intenta de nuevo"); // Mensaje por defecto si se
																							// indica
																							// una opción diferente a 1,
																							// 2, 4, 5, 6 o 7
				}

			} catch (InputMismatchException e) { // Captura una excepción si introducimos algo que no sea un número
													// entero
				System.out.println("Valor erróneo. Vuelva a intentarlo");
				scanner.nextLine();

			}

		}
		scanner.close(); // Cerramos el scanner del método principal

	}

	private static void addEstudiante(File archivo, String apellidos, String nombre, int edad) { // Método estático para
																									// añadir estudiante
		// al listado. Al ser estático, se le tienen que pasar las variables con las que
		// tiene que trabajar. No puede acceder a ellas desde dentro del método.

		try (FileWriter fw = new FileWriter(archivo, true); BufferedWriter bw = new BufferedWriter(fw)) { // Crea
			// el
			// archivo
			// estudiantes.txt
			bw.write(apellidos + ", " + nombre + ", " + edad + " años."); // Escribe en el archivo estudiantes.txt
																			// el
																			// apellido, nombre y edad de los
			// estudiantes con la estructura indicada
			bw.newLine(); // Limpiamos el buffer del BufferedWriter para evitar errores posteriores al
							// mantener el valor de la variable en memoria
			bw.close(); // Cerramos el BufferedWriter

		} catch (IOException e) { // Captura la excepción de tipo input o output
			System.out.println("Ocurrió un error al escribir el archivo."); // Mensaje que devuelve en caso de que
																			// se
																			// capture la excepción
		}

	}

	private static void listarEstudiantes(File archivo) { // Método estático para listar los estudiantes añadidos al
															// archivo

		try (FileReader fr = new FileReader(archivo); BufferedReader br = new BufferedReader(fr)) {
			String str; // Añadirá la información escrita en el archivo a un objeto de
						// tipo String denominado str

			List<String> listado = new ArrayList<>(); // Inicializamos un ArrayList denominado listado

			while ((str = br.readLine()) != null) { // Mientras el objeto de tipo String str tenga líneas de texto,
													// imprimirá esas líneas
				listado.add(str); // Añadirá las diferentes líneas al ArrayList listado

			}

			if ((str = br.readLine()) == null) { // Si no hay información escrita en el archivo, no se añadirán líneas
													// al str y no se añadirán igualmente al ArrayList
				System.out.println("El listado está vacío. No hay estudiantes que mostrar"); // Mensaje que muestra en
																								// ese caso
			}

			Collections.sort(listado); // Recopilamos y ordenamos por orden alfabético los elementos incluidos en
										// listado

			for (String estudiante : listado) {
				System.out.println(estudiante); // Bucle for each para que cada línea del ArrayList listado genere una
												// variable estudiante
			}

		} catch (FileNotFoundException e) { // Excepción capturada si no encuentra el archivo
			System.out.println(e.getMessage());
		} catch (IOException e) { // Excepción capturada de tipo inputo/output
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	private static void eliminarListado(File archivo, Scanner scanner) { // Método estático para eliminar el
		// listado

		System.out.println("¿Está seguro de que desea eliminar el listado de estudiantes?");
		System.out.println("1. Sí, deseo eliminar el listado");
		System.out.println("2. No, deseo volver al menú principal");

		int opcion2 = scanner.nextInt(); // Se crea el objeto de tipo entero opcion2 que tomará el valor introducido
											// por
											// teclado gracias a la clase Scanner

		switch (opcion2) { // Bucle switch
		case 1:
			scanner.nextLine(); // Cambia de línea al introducir la opción y limpia el buffer
			archivo.delete(); // Elimina el objeto archivo, es decir, estudiantes.txt

			if (archivo.exists()) {
				System.out.println("Error al eliminar el archivo"); // Si, una vez dada la orden de eliminar, el
																	// archivo existe, devuelve un mensaje de error
			} else {
				System.out.println("El archivo se ha eliminado correctamente"); // Si comprueba que ya no existe, es
																				// que se ha eliminado correctamente
																				// y devuelve el mensaje
																				// correspondente
			}
			break;
		case 2:
			scanner.nextLine();
			break; // En caso de escoger la opción 2, sale del bucle y volverá al bucle switch
					// original del método main ya que no hemos cambiado el valor de continuar a
					// false
		default:
			// scanner.nextLine();
			System.out.println("Opción no válida. Por favor, intenta de nuevo"); // Mensaje por defecto si no se
																					// escogen
																					// las opciones 1 o 2

		}

	}

	private static void buscarEstudiante(File archivo, String apellidosBuscados, String nombreBuscado) { // Método
																											// estático
																											// para
																											// buscarEstudiante.
																											// Utilizamos
																											// el
																											// archivo y
																											// las
																											// variables
																											// introducidas
																											// por
																											// teclado
																											// en e
																											// método
																											// principal

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"))) { // InsputStreamReader
																														// y
																														// FileInputStream
																														// en
																														// vez
																														// de
																														// FileReader
																														// porque
																														// me
																														// daba
																														// errores
																														// al
																														// introducir
																														// elementos
																														// con
																														// caracteres
																														// especiales
																														// (tildes,
																														// ñ,
																														// etc.).
																														// Así,
																														// puedo
																														// darle
																														// la
																														// orden
																														// de
																														// que
																														// codifique
																														// en
																														// UTF-8

			String str; // Declaro la variable de tipo String str

			boolean encontrado = false; // Inicializo la variable booleana encontrado como false

			while ((str = br.readLine()) != null) { // Inicializo la variable str, de manera que adquiere el valor de
													// las fierentes líneas que ha leído en el archivo. Bucle mientras
													// haya líneas en str
				String partes[] = str.split(","); // Separo en partes lo que está separado en el archivo por comas
				if (partes.length >= 3) { // Si la línea tiene 3 o más partes (una para apellido, otra para nombre y
											// otra para edad), la línea es válida para lo que necesitamos en este
											// método
					String apellidos = partes[0].trim(); // Inicializo la variable apellidos como aquello contenido en
															// la parimera parte tras la separación anterior
					String nombre = partes[1].trim(); // Inicializo la variable nombre como lo contenido en la segunda
														// parte (al separarlo en un tipo de array, la primera línea o
														// parte es 0, no 1

					if (apellidos.equalsIgnoreCase(apellidosBuscados) && nombre.equalsIgnoreCase(nombreBuscado)) { // Compara
																													// los
																													// apellidos
																													// y
																													// el
																													// nombre
																													// introducidos
																													// por
																													// teclado
																													// con
																													// lo
																													// que
																													// ha
																													// obtenido
																													// tras
																													// la
																													// separación
																													// en
																													// partes
																													// de
																													// lo
																													// leído
																													// en
																													// el
																													// archivo

						System.out.println("Estudiante encontrado: " + str); // Si encuentra una equivalencia, imprime
																				// la línea del archivo en la que había
																				// coincidencia
						encontrado = true; // Cambiamos el valor de encontrado a true

					}

				}

			}

			if (!encontrado) { // Mensaje si no lo hemos encontrado
				System.out.println("Estudiante no encontrado");
			}

			br.close(); // Cerramos el BufferedReader

		} catch (FileNotFoundException e) { // Captura la excepción si no encuentra el archivo
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) { // Captura la excepción genérica de tipo input/output
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void actualizarEdadEstudiante(File archivo, String apellidosBuscados, String nombreBuscado,
			Scanner scanner) {
		// Método
		// estático
		// para
		// actualizar
		// edad
		// del
		// estudiante

		int edadActualizada; // Declaramos la variable edadActualizada de tipo entero
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"))) { // Codificamos
																														// en
																														// UTF-8
																														// como
																														// en
																														// el
																														// método
																														// anterior

			String str;

			List<String> nuevoListado = new ArrayList<>(); // Reutilizamos parte del código de listarEstudiantes.
															// Creamos el ArrayList nuevoListado
			boolean encontrado = false;

			while ((str = br.readLine()) != null) {

				String partes[] = str.split(",");
				if (partes.length >= 3) {
					String apellidos = partes[0].trim();
					String nombre = partes[1].trim();

					if (apellidos.equalsIgnoreCase(apellidosBuscados) && nombre.equalsIgnoreCase(nombreBuscado)) { // Reutilizamos
																													// parte
																													// del
																													// código
																													// de
																													// buscarEstudiante

						System.out.println("Estudiante encontrado: " + str);
						System.out.println("Escriba la edad correcta del estudiante");
						edadActualizada = scanner.nextInt();

						partes[2] = edadActualizada + " años"; // Como la edad debe estar en la tercera parte separada
																// de la línea, hacemos que esa parte tome el valor
																// introducido por teclado y le añadimos "años" ya que
																// va a sustituir a toda la última parte después de la
																// coma
						String nuevaLinea = String.join(", ", partes); // Inicializamos la variable nuevaLinea con la
																		// clase String.join, que permite unir las
																		// partes en una nueva línea, separándolas de
																		// nuevo por comas
						nuevoListado.add(nuevaLinea); // Añadimos la información contenida en nuevaLínea al ArrayList
														// nuevoListado
						encontrado = true; // Cambiamos el valor de encontrado a true para que salga
						// del bucle while

					} else {
						// Si el estudiante no coincide, se mantiene la línea original

						nuevoListado.add(str);

					} // Hacemos que todos los datos, tanto los modificados como los originales, se
						// añadan al ArrayList nuevoListado

				}

			}

			if (!encontrado) { // Mensaje si no se encuentra al estudiante
				System.out.println("Estudiante no encontrado.");
			}

			else { // Si sí que lo encuentra, significa que hay que modificar el archivo. Solo se
					// ejecuta esta parte en ese caso
				try (FileWriter fw = new FileWriter(archivo); BufferedWriter bw = new BufferedWriter(fw)) {
					for (String linea : nuevoListado) { // Cada línea de nuevoListado se imprime en una variable linea
						bw.write(linea); // Se utiliza BufferedWriter para escribir esas líneas
						bw.newLine(); // Añadimos una nueva línea después de cada línea
					} // Va a sobreescribir lo contenido en el archivo estudiantes.txt
					System.out.println("Archivo actualizado correctamente.");
					bw.close(); // Cerramos BufferedWriter

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}
	}

	private static void eliminarEstudiante(File archivo, String apellidosBuscados, String nombreBuscado,
			Scanner scanner) { // Método
		// estático
		// para
		// eliminarEstudiante

		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"))) {

			String str; // Reutilizamos código de métodos anteriores

			File archivoTemporal = File.createTempFile("tempestudiantes", ".txt"); // Creamos el archivo temporal
																					// tempestudiantes.txt

			boolean encontrado = false;

			try (FileWriter fw = new FileWriter(archivoTemporal); BufferedWriter bw = new BufferedWriter(fw)) { // Reutilizamos
																												// código
																												// del
																												// método
																												// listarEstudiantes.
																												// No
																												// hace
																												// falta
																												// codificar
																												// en
																												// UTF-8
																												// ya
																												// que
																												// solo
																												// hay
																												// que
																												// introducir
																												// números
																												// enteros

				while ((str = br.readLine()) != null) {// Reutilizamos código del método buscarEstudiante

					String partes[] = str.split(",");

					if (partes.length >= 3) {

						String apellidos = partes[0].trim();
						String nombre = partes[1].trim();

						if (apellidos.equalsIgnoreCase(apellidosBuscados) && nombre.equalsIgnoreCase(nombreBuscado)) {

							System.out.println("Estudiante encontrado: " + str); // Reutilizamos código del método
																					// buscarEstudiante

							System.out.println("¿Está seguro de que desea eliminar a este estudiante? Escriba S o N");

							String respuesta = scanner.nextLine(); // La respuesta toma el valor introducido por teclado

							if (respuesta.equalsIgnoreCase("S")) { // Si se introduce S, en mayús
								encontrado = true; // Cambia el valor de encontrado a true
								continue; // En este caso, obviará esta línea y no la escribirá, pero sí
											// escribirá el
								// resto
							}

						}

					}

					bw.write(str); // Escribe todas las líneas salvo la que le hemos dicho que elimine gracias a la
					// orden continue
					bw.newLine(); // Cambiamos de línea

				}

				bw.close();
				fw.close();

			}

			if (!encontrado) {
				System.out.println("Estudiante no encontrado");

			} else {
				br.close();

				archivo.delete(); // Eliminamos el archivo original. Para esto es para lo que es necesario cerrar
									// el BufferedReader. Deja de estar en uso por la memoria buffer y lo podemos
									// eliminar
				archivoTemporal.renameTo(archivo); // Renombramos el archivo temporal como el archivo original

				System.out.println("Estudiante eliminado correctamente"); // Si lo puede eliminar, devuelve el
																			// mensaje
																			// correspondiente
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}
	}

}
