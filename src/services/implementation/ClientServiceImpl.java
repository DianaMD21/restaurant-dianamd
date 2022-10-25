package services.implementation;

import enums.StatusEnum;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import services.interfaces.ClientService;
import util.ClientServiceUtility;
import util.UserUtil;

public class ClientServiceImpl implements ClientService {
  private final List<entities.Client> clients;
  private Long currentId;

  public ClientServiceImpl() {
    this.clients = new ArrayList<>();
    this.currentId = 0L;
  }

  @Override
  public Optional<entities.Client> insert(entities.Client client) {
    client.setId(this.currentId);
    client.setCode(ClientServiceUtility.createCode(this.currentId));
    client.setStatus(StatusEnum.ACTIVE);
    client.setCreatedAt(Instant.now());
    client.setUpdatedAt(Instant.now());
    this.currentId = this.currentId + 1;
    this.clients.add(client);
    return Optional.of(client);
  }

  @Override
  public Optional<entities.Client> findById(Long id) {
    Optional.ofNullable(id).orElseThrow(IdNullPointerException::new);
    var client =
        this.clients.stream()
            .filter(c -> id.equals(c.getId()) && !(c.getStatus() == StatusEnum.DELETED))
            .findAny()
            .orElseThrow(() -> new EntityNotFoundException(entities.Client.class, id));
    return Optional.ofNullable(client);
  }

  @Override
  public Optional<entities.Client> delete(Long id) {
    Optional.ofNullable(id).orElseThrow(IdNullPointerException::new);
    var client = this.findById(id).get();
    client.setStatus(StatusEnum.DELETED);
    client.setUpdatedAt(Instant.now());
    return Optional.of(client);
  }

  @Override
  public Optional<entities.Client> update(entities.Client entity) {
    Optional.ofNullable(entity).orElseThrow(NullEntityException::new);
    Optional.ofNullable(entity.getId()).orElseThrow(IdNullPointerException::new);
    var client = this.findById(entity.getId()).get();
    entity.setUpdatedAt(Instant.now());
    UserUtil.fetchUser(client, entity);
    return Optional.of(client);
  }

  @Override
  public List<entities.Client> findAll() {
    return this.clients.stream()
        .filter(c -> c.getStatus() != StatusEnum.DELETED)
        .collect(Collectors.toList());
  }
}
