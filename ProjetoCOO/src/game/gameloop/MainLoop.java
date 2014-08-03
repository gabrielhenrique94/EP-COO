package game.gameloop;
import interfaces.GameRules;


/*************************************************************************************************/
/*                                                                                               */
/* Main loop do jogo                                                                             */
/*                                                                                               */
/* O main loop do jogo possui executa as seguintes operações:                                    */
/*                                                                                               */
/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
/*                                                                                               */
/* 2) Atualiza estados dos elementos baseados no tempo que correu desde a última atualização     */
/*    e no timestamp atual: posição e orientação, execução de disparos de projéteis, etc.        */
/*                                                                                               */
/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
/*                                                                                               */
/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
/*                                                                                               */
/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
/*                                                                                               */
/*************************************************************************************************/

public class MainLoop implements Runnable {
	private GameRules rules = null;
	
	/* Indica que o jogo está em execução */
	private ThreadLocal<Boolean> running = new ThreadLocal<Boolean>();
	
	public MainLoop(GameRules rules) {
		this.rules = rules;
	}
	
	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time.    */
	private static void busyWait(long time){
		// TODO Auto-generated method stub
		while(System.currentTimeMillis() < time) Thread.yield();
	}
	
	@Override
	public void run() {
		/* variáveis usadas no controle de tempo efetuado no main loop */
		//escopo local, para ser thread safe
		
		running.set(true);
		long delta;
		long currentTime = System.currentTimeMillis();

		while(running.get()){
			/* Usada para atualizar o estado dos elementos do jogo    */
			/* (player, projéteis e inimigos) "delta" indica quantos  */
			/* ms se passaram desde a última atualização.             */
			
			delta = System.currentTimeMillis() - currentTime;
			
			/* Já a variável "currentTime" nos dá o timestamp atual.  */
			
			currentTime = System.currentTimeMillis();
			
			rules.processStep();
			rules.processUserInput();
			rules.draw();
			busyWait(currentTime + 5);
		}	
	}
	
	public void stop(){
		running.set(false);
	} 
}
