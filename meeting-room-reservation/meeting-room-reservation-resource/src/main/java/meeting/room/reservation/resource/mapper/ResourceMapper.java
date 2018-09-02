package meeting.room.reservation.resource.mapper;

import java.util.Collection;

/**
 * 
 * Resource Mapper
 * 
 * @author bizuma
 *
 * @param <D> Domain
 * @param <R> Resource
 */
public interface ResourceMapper<D, R> {

	R map(D domain);

	Collection<R> mapCollection(Collection<D> domainCollection);
}
