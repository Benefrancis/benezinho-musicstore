package br.com.fiap.domain.dto;

import br.com.fiap.domain.entity.Artist;
import br.com.fiap.domain.service.ArtistService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record ArtistDTO(Long id,
                        @NotNull(message = "O nome do artista deve ser informado") String name,
                        String nationality) {

    private static ArtistService service = new ArtistService();

    public static Artist of(ArtistDTO dto) {

        // É nulo?
        if (Objects.isNull( dto ))
            throw new RuntimeException( "Objeto DTO não pode estar nulo. Verifique se  incluiu o Json no Body na requisição" );

        //Ele informou o id?
        if (Objects.nonNull( dto.id )) return service.findById( dto.id );

        //Se não informou o Id é porque está salvando um novo Artist
        Artist artist = new Artist();
        artist.setId( null );
        artist.setName( dto.name );
        artist.setNationality( dto.nationality );

        return artist;
    }


    public static ArtistDTO of(Artist entity) {
        return new ArtistDTO( entity.getId(), entity.getName(), entity.getNationality() );
    }


    @Override
    public String toString() {
        return "ArtistDTO{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
