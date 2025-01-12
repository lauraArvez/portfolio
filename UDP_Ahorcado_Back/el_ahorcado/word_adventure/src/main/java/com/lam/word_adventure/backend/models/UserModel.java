package com.lam.word_adventure.backend.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase entidad UserModel
 *
 * @author Laura Arvez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class UserModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //autogenerado

    //@ExistsByUsername //anotación personalizada
    @Column(unique = true)
    @NotBlank
    @Size(min=3, max =25)
    private String username;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //oculta en el response
    @Size(min=6)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="user_roles", // tabla intermedia generada con la relación
        joinColumns = @JoinColumn(name="user_id"), //clave foránea de user
        inverseJoinColumns = @JoinColumn(name="role_id") // clave foránea de role
    )
    private Set<RoleModel> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private LocationModel location;
    
    private int age;

    @ManyToOne
    @JoinColumn(name = "sex_id") 
    private SexModel sex;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ScoreModel> scores = new ArrayList<>();

    //para estadísticas de juego: partidas jugadas
    private int gamesPlayed;
    private int totalScore;
    


    //::::::::::::::::::::::::::::::::::::::::::::   CONSTRUCTORES  ::::::::::::::::::::::::::::::::::::::::::::::::::::
    
    /**
     * constructor para el registerRequest
     * @param username usuario, debe tener entre 3 y 25 caracteres (no nulo ni vacío).
     * @param password password del usuario, debe tener al menos 6 caracteres (no nulo ni vacío).
     * @param location objeto LocationModel que representa la localización del usuario.
     * @param age edad del usuario.
     * @param sex objeto SexModel que representa el género del usuario.
     * @param roles roles asignados al usuario.
     */
    public UserModel(@NotBlank @Size(min = 3, max = 25) String username, @NotBlank @Size(min = 6) String password,
    LocationModel location, int age, SexModel sex, Set<RoleModel> roles)  {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.location= location;
        this.age = age;
        this.sex = sex;
    }

//:::::::::::::::::::::::::::::::::::::::::::  ROLES  ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * borra los roles del usuario
     *
     * @param role role que se elimanará del usuario
     */
	public void deleteRole(RoleModel role) {
        this.roles.remove(role);
    }

    /**
     * obtiene los roles del usuario
     *
     * @return List de nombres de roles del usuario
     */
    public List<String> getRoleNames() {
        return roles.stream()
                    .map(RoleModel::getRole) // Mapear cada RoleModel al nombre del rol
                    .collect(Collectors.toList()); // Recopilar en una lista de cadenas
    }

//:::::::::::::::::::::::::::::::::::::::::::  SCORE :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * método para actualizar la puntuación de juego
     *
     * @param newScore la nueva puntuación
     */
    public void updateScoreGame(int newScore) {
        // Crear una nueva puntuación asociada al usuario
        if (this.scores == null) {
            this.scores = new ArrayList<>();
        }
        ScoreModel newScoreModel = new ScoreModel(); // nueva instancia de ScoreModel asociada al user actual
        newScoreModel.setScore(newScore);
        newScoreModel.setDate(LocalDateTime.now());
        newScoreModel.setUser(this);
        this.scores.add(newScoreModel);// agrega a la lista de puntuaciones del user la nueva puntuación

        // Actualizar estadísticas de juego
        this.gamesPlayed++;
        this.calculateTotalScore();
    }

    /**
     * Calcula, actualiza el total de puntos después de cada nueva puntuación
     */
    private void calculateTotalScore(){
        this.totalScore = this.scores.stream()
            .mapToInt(ScoreModel::getScore)
            .sum();
    }
    /**
     * obtener ranking de puntuaciones del usuario, ordenado de mayo a menor
     *
     * @return String del ranking de puntuaciones del usuario
     */
    public String getScoreRanking(){
        List<ScoreModel> scoresSorted= this.getScores().stream()
            .sorted(Comparator.comparing(ScoreModel::getScore).reversed())
            .collect(Collectors.toList());
        return ScoreModel.buildScoreListString(scoresSorted);
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserModel other = (UserModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

}
