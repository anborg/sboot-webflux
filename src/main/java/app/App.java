package app;

import app.test.GreetingClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.Arrays;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class App {
	private static final Log LOGGER = LogFactory.getLog(App.class);


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		GreetingClient greetingClient = context.getBean(GreetingClient.class);
		// We need to block for the content here or the JVM might exit before the message is logged
		System.out.println(">> message = " + greetingClient.getMessage().block());
	}

	@EventListener
	public void handleContextRefreshed(ContextRefreshedEvent event) {
		printActiveProperties((ConfigurableEnvironment) event.getApplicationContext().getEnvironment());
	}

	private void printActiveProperties(ConfigurableEnvironment env) {
		StringBuilder envStr = new StringBuilder("======= ENV =============");
		MutablePropertySources propSrcs = env.getPropertySources();
		Map<String,String> propMap = StreamSupport.stream(propSrcs.spliterator(), false)
				.filter(ps -> ps instanceof EnumerablePropertySource)
				.map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
				.flatMap(Arrays::stream)
				.distinct().collect(Collectors.toMap(Function.identity(), env::getProperty));
		SortedSet<String> keys = new TreeSet<>(propMap.keySet());
		String envStr2 = keys.stream().map(key -> "APPENV "+key + "\t="+ propMap.get(key) + "\n").collect(Collectors.joining());
		envStr.append(envStr2+"\n======= ENV Ends =============");
		LOGGER.info(envStr);
	}
}