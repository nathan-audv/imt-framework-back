package imt.framework.back.imtframeworkback.data.services.impl;

import imt.framework.back.imtframeworkback.data.models.FavoriteModel;
import imt.framework.back.imtframeworkback.data.repositories.FavoriteRepository;
import imt.framework.back.imtframeworkback.data.services.FavoriteService;
import imt.framework.back.imtframeworkback.domain.models.Favorite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;

    @Override
    public Favorite save(Favorite favorite) {
        return Favorite.fromData(favoriteRepository.save(FavoriteModel.fromDomain(favorite)));
    }

    @Override
    public List<Favorite> findByUser(Integer userId) {
        return favoriteRepository.findAllByUser_UserId(userId).stream().map(Favorite::fromData).toList();
    }

    @Override
    public Optional<Favorite> findByUserAndDish(Integer userId, Integer dishId) {
        return favoriteRepository.findByUser_UserIdAndDish_DishId(userId, dishId).map(Favorite::fromData);
    }

    public void remove(Favorite favorite){
        favoriteRepository.delete(FavoriteModel.fromDomain(favorite));
    }
}
