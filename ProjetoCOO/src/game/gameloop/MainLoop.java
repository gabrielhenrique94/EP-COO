package game.gameloop;
import lib.GameLib;
import game.gamerule.GameRule;
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

public class MainLoop {
	private GameRules rules = null;
	
	private static MainLoop instance = new MainLoop(new GameRule());
	
	private long delta;
	private long currentTime = System.currentTimeMillis();

	private boolean running = false;
	
	public static MainLoop getInstance(){
		return instance;
	}
	
	
	private MainLoop(GameRules rules) {
		this.rules = rules;
	}
	
	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time.    */
	private static void busyWait(long time){
		// TODO Auto-generated method stub
		while(System.currentTimeMillis() < time) Thread.yield();
	}

	public void run() {
		/* variáveis usadas no controle de tempo efetuado no main loop */
		
		while(running){
			/* Usada para atualizar o estado dos elementos do jogo    */
			/* (player, projéteis e inimigos) "delta" indica quantos  */
			/* ms se passaram desde a última atualização.             */
			
			delta = System.currentTimeMillis() - currentTime;
			
			/* Já a variável "currentTime" nos dá o timestamp atual.  */
			
			currentTime = System.currentTimeMillis();
			
			if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) stop();
			
			rules.processStep();
			rules.processUserInput();
			rules.draw();
			
			GameLib.display();
			
			busyWait(currentTime + 5);
		}	
		
		System.exit(0);
	}
	
	public long getDelta() {
		return delta;
	}
	
	public long getCurrentTime() {
		return currentTime;
	}
	
	public GameRules getRules() {
		return rules;
	}
	
	public void stop(){
		running = false;
	} 
}
