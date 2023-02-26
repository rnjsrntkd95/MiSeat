import repo from '@repository';
import { useQuery } from '@tanstack/react-query';
import getFormatDate from '@utils/date';
import SpaceMock from './SpaceMockRepo';

const key = 'space';

const repository = new SpaceMock();
// const repository = repo.remoteSpaceRepo;

const fetchSpace = async (date: string) => {
  const { data } = await repository.fetchSpace(date);
  return data;
};

export function useSpace(date: Date) {
  return useQuery([key], () => fetchSpace(getFormatDate(date)));
}
