package ru.dionisius.service;

import ru.dionisius.data.models.Ad;

import java.util.List;

/**
 * Created by Dionisius on 11.11.2017.
 */
public interface IAdService {
    public long addAd(Ad ad);
    public void updateAd(Ad ad);
    public List<Ad> getAll();
    public Ad getAdById(long id);
    public void removeAd(long id);
}
