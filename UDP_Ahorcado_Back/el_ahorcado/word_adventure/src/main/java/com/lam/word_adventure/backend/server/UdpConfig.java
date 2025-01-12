package com.lam.word_adventure.backend.server;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * configura un entorno para recibir mensajes UDP en un puerto específico y
 * procesarlos en hilos manejados por un TaskExecutor,
 * todo dentro del contexto de Spring Framework y Spring Integration.
 *
 * * <p>
 * Esta configuración utiliza Spring Integration para manejar la recepción de mensajes UDP
 * y la gestión de hilos para su procesamiento.
 * </p>
 *
 * @author Laura Arvez
 */
@Configuration
@EnableIntegration
public class UdpConfig {


    @Value("${udp.channel}")
    private String channel;
    @Value("${udp.port}")
    private Integer port;

    @Value("${udp.task-executor.core-pool-size}")
    private Integer corePoolSize;
    @Value("${udp.task-executor.max-pool-size}")
    private Integer maxPoolSize;
    @Value("${udp.task-executor.queue-capacity}")
    private Integer queueSize;

    

    /**
     * constructor por defecto de UdpConfig
     */
    public UdpConfig() {
    }

    /**
     * inboundChannel() y outboundChannel(): Estos métodos producen beans de tipo DirectChannel,
     * que son canales de mensajería directa utilizados para la comunicación dentro del framework
     * de Spring Integration.
     * @return MessageChannel
     */
    @Bean
    public MessageChannel inboundChannel() {
        return new DirectChannel();
    }

    /**
     * inboundChannel() y outboundChannel(): Estos métodos producen beans de tipo DirectChannel,
     * que son canales de mensajería directa utilizados para la comunicación dentro del framework
     * de Spring Integration.
     * @return MessageChannel
     */
    @Bean
    public MessageChannel outboundChannel() {
        return new DirectChannel();
    }
    
    
    /**
     * crea y configura un adaptador de canal de Spring Integration que escucha
     * en un puerto específico para recibir mensajes unicast
     * @return el adaptador de canal de recepción UDP
     */
    @Bean(name = "udpReceivingAdapter")
    public UnicastReceivingChannelAdapter udpReceivingAdapter() {
        
        //escucha en un puerto UDP específico (port).
        //Los mensajes recibidos en este puerto se envían al canal de entrada (inboundChannel())
        UnicastReceivingChannelAdapter adapter = new UnicastReceivingChannelAdapter(port);
        adapter.setOutputChannel(inboundChannel());
        adapter.setTaskExecutor(getTaskExecutor()); // será utilizado para manejar los hilos que procesan los mensajes entrantes
        adapter.setOutputChannelName(channel);
        return adapter;
    }

    // este ejecutor de tareas definirá cuántas conexiones simultáneas UDP puede manejar
    TaskExecutor getTaskExecutor() {

        ThreadPoolTaskExecutor ioExec = new ThreadPoolTaskExecutor();
        ioExec.setCorePoolSize(corePoolSize);
        ioExec.setMaxPoolSize(maxPoolSize);
        ioExec.setQueueCapacity(queueSize);
        ioExec.setThreadNamePrefix("io-");
        ioExec.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        ioExec.initialize();
        return ioExec;
    }


}
