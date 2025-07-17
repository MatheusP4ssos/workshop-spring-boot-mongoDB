package com.matheusHolanda.workshopMongo.config;

import com.matheusHolanda.workshopMongo.domain.User;
import com.matheusHolanda.workshopMongo.dto.AuthorDTO;
import com.matheusHolanda.workshopMongo.dto.CommentDTO;
import com.matheusHolanda.workshopMongo.entities.Post;
import com.matheusHolanda.workshopMongo.repository.PostRepository;
import com.matheusHolanda.workshopMongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));


        Post post1 = new Post(null, sdf.parse("21/03/2025 21:00:00"), "Hora das férias!", "Finalmente as tão aguardadas férias", new AuthorDTO(maria.getId(), maria.getName()));
        Post post2 = new Post(null, sdf.parse("23/05/2025 21:00:00"), "Jogando o novo Death Stranding", "Zerando Death Stranding 2, um nojo desse Kojima", new AuthorDTO(alex.getId(), alex.getName()));

        CommentDTO c1 = new CommentDTO("Só descansar agora", sdf.parse("21/03/2025 00:14:00"), new AuthorDTO(alex.getId(), alex.getName()));
        CommentDTO c2 = new CommentDTO("Se beber, não dirija!", sdf.parse("22/03/2025 12:14:00"), new AuthorDTO(bob.getId(), bob.getName()));
        CommentDTO c3 = new CommentDTO("Jogo de lacrola", sdf.parse("25/03/2025 16:14:00"), new AuthorDTO(maria.getId(), maria.getName()));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
