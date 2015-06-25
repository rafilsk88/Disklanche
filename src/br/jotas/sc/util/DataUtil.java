package br.jotas.sc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DataUtil {
	
	public enum Mes {
		JANEIRO,
		FEVEREIRO,
		MARCO,
		ABRIL,
		MAIO,
		JUNHO,
		JULHO,
		AGOSTO,
		SETEMBRO,
		OUTUBRO,
		NOVEMBRO,
		DEZEMBRO
	}
	
	protected DataUtil(){
		
	}
	
	private static DataUtil instance;  
	
	public static DataUtil getInstance(){		
		if(instance == null)
			instance = new DataUtil();		
		return instance;
	}

	protected static void setInstance(DataUtil dataUtil) {
		instance = dataUtil;
	}	
	 
	//Deve ser utilizado para criar uma data atual com horas, minutos e segundos zerados, com o proposito de gerar uma data com o primeiro instante do dia.
	public static Date criarDataAtualNoPrimeiroSegundo(){
		return criarCalendarDataAtualNoPrimeiroSegundo().getTime();
	}
	
	public static boolean mesmoMesAtual(Date data){
		Calendar dataAtual = Calendar.getInstance();
		Calendar dataComparacao = Calendar.getInstance();
		dataComparacao.setTime(data);		
		if(dataAtual.get(Calendar.MONTH)==dataComparacao.get(Calendar.MONTH)){
			return true;			
		}
		return false;		
	}

	public static boolean mesmoAnoAtual(Date data){
		Calendar dataAtual = Calendar.getInstance();
		Calendar dataComparacao = Calendar.getInstance();
		dataComparacao.setTime(data);		
		if(dataAtual.get(Calendar.YEAR)==dataComparacao.get(Calendar.YEAR)){
			return true;			
		}
		return false;		
	}
	
	public static Date criarDataAtualNoUltimoSegundo(){
		
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.set(Calendar.HOUR_OF_DAY, 23);
		dataAtual.set(Calendar.MINUTE, 59);
		dataAtual.set(Calendar.SECOND, 59);
		dataAtual.set(Calendar.MILLISECOND, 999);

		return dataAtual.getTime();
	}
	
	public static Date criarNoPrimeiroSegundo(Date data){
		if(data==null){
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	
	public static Date criarNoUltimoSegundo(Date data){
		if(data==null){
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTime();
	}

	public static int diferencaEmdias(Date d1, Date d2) {
		int MILLIS_IN_DAY = 86400000;

		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		c1.set(Calendar.MILLISECOND, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.HOUR_OF_DAY, 0);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		c2.set(Calendar.MILLISECOND, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		
		return (int) ((c1.getTimeInMillis() - c2.getTimeInMillis()) / MILLIS_IN_DAY);
	}
	
	public static boolean verificaSeDiferencaAnosSuperior(Date menorData, Date maiorData, int totalAnos) {		    
		
		Calendar menorDataControle = Calendar.getInstance();
		menorDataControle.setTime(menorData);
		menorDataControle.set(Calendar.MILLISECOND, 0);
		menorDataControle.set(Calendar.SECOND, 0);
		menorDataControle.set(Calendar.MINUTE, 0);
		menorDataControle.set(Calendar.HOUR_OF_DAY, 0);
		menorDataControle.add(Calendar.YEAR, totalAnos);		
		Date menorDataFormatada = menorDataControle.getTime(); 
		
		Calendar maiorDataControle = Calendar.getInstance();
		maiorDataControle.setTime(maiorData);
		maiorDataControle.set(Calendar.MILLISECOND, 0);
		maiorDataControle.set(Calendar.SECOND, 0);
		maiorDataControle.set(Calendar.MINUTE, 0);
		maiorDataControle.set(Calendar.HOUR_OF_DAY, 0);		
		Date maiorDataFormatada = maiorDataControle.getTime();

		
		if (!menorDataFormatada.after(maiorDataFormatada))
			return true;
		return false;		  
	}
	
	
	public static Date criarDataNoUltimoDiaMesNoUltimoSegundo(Date data){		
		Calendar dataReferenceia = Calendar.getInstance();
		dataReferenceia.setTime(data);
		
		Calendar dataAtual = GregorianCalendar.getInstance();
		dataAtual.setTime( data );		
		dataReferenceia.set(Calendar.DAY_OF_MONTH, dataAtual.getActualMaximum( Calendar.DAY_OF_MONTH ));		
		dataReferenceia.set(Calendar.HOUR_OF_DAY, 23);
		dataReferenceia.set(Calendar.MINUTE, 59);
		dataReferenceia.set(Calendar.SECOND, 59);
		dataReferenceia.set(Calendar.MILLISECOND, 999);

		return dataReferenceia.getTime();
	}
	public static Date criarDataNoPrimeiroDiaMesNoPrimeiroSegundo(Date data){		
		Calendar dataReferenceia = Calendar.getInstance();
		dataReferenceia.setTime(data);
		
		Calendar dataAtual = GregorianCalendar.getInstance();
		dataAtual.setTime( data );		
		dataReferenceia.set(Calendar.DAY_OF_MONTH, 1);		
		dataReferenceia.set(Calendar.HOUR_OF_DAY, 0);
		dataReferenceia.set(Calendar.MINUTE, 0);
		dataReferenceia.set(Calendar.SECOND, 0);
		dataReferenceia.set(Calendar.MILLISECOND, 0);
		
		return dataReferenceia.getTime();
	}
	
	public static String criarDataDiaMesAno(Date data){
		Calendar dataReferenceia = Calendar.getInstance();
		dataReferenceia.setTime(data);
		return dataReferenceia.get(Calendar.YEAR) + "-" + dataReferenceia.get(Calendar.MONTH) + "-" + dataReferenceia.get(Calendar.DAY_OF_MONTH);
	}
	
	public static String formataDataEHora(Date data){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");		
		return dateFormat.format(data);
	}	
	
	public static Calendar criarCalendarCom(int dia, Mes mes, int ano){
		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);

		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.MONTH, mes.ordinal());
		calendar.set(Calendar.DAY_OF_MONTH, dia);

		return calendar;
	}
	
	public static Calendar criarCalendarNoPrimeiroSegundoCom(int dia, Mes mes, int ano){
		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);
		
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.MONTH, mes.ordinal());
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar;
	}
	
	public Calendar criarCalendarComDataDoSitema(){
		return Calendar.getInstance();
	}

	public static Calendar criarCalendarDataAtualNoPrimeiroSegundo(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar;
	}
	
	public static Date criarDataCom(int dia, Mes mes, int ano){
		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);

		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.MONTH, mes.ordinal());
		calendar.set(Calendar.DAY_OF_MONTH, dia);

		return calendar.getTime();
	}
	
	public static Date criarDataAlterandoMes(int mes, Date date){
		if(date==null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, mes);
		return calendar.getTime();
	}
	
	public static Date criarDataAlterandoDia(int dia, Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dia);
		return calendar.getTime();
	}

	public static Date criarDataAlterandoAno(int ano, Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, ano);
		return calendar.getTime();
	}

	public static Date criarDataParametroHora(Date data, Integer hora){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		return calendar.getTime();
	}
	
	public static Date criarDataParametroMinuto(Date data, Integer minuto){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MINUTE, minuto);
		return calendar.getTime();
	}
	
	public static Date criarDataCom(int dia, Mes mes, int ano, int hora){
		Calendar calendar = Calendar.getInstance();
		calendar.setLenient(false);
		
		calendar.set(Calendar.YEAR, ano);
		calendar.set(Calendar.MONTH, mes.ordinal());
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.HOUR_OF_DAY, hora);
		
		return calendar.getTime();
	}
	
	public static Date criarDataApartirDaString(String data) throws ParseException{
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();		 
		c.setTime(formatoData.parse(data));
		return c.getTime();
	}	
}
