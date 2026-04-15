package com.juhmaran.restmvc.mappers;

import com.juhmaran.restmvc.entities.Beer;
import com.juhmaran.restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * spring-rest-mvc
 *
 * @author Juliane Maran
 * @since 14/04/2026
 */
@Mapper
public interface BeerMapper {

  Beer beerDtoToBeer(BeerDTO beerDTO);

  BeerDTO beerToBeerDto(Beer beer);

}
