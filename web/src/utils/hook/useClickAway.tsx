'use client';

import { MutableRefObject, useEffect, useRef } from 'react';

type EventType = MouseEvent | TouchEvent;

export default function useClickAway(
  onClickAway: () => void,
  target: MutableRefObject<HTMLElement | null>
) {
  const onClickAwayRef = useRef(onClickAway);
  onClickAwayRef.current = onClickAway;

  useEffect(() => {
    const handler = (event: any) => {
      if (target.current?.contains(event.target)) return;
      onClickAwayRef.current();
    };

    document.addEventListener('click', handler);

    return () => document.removeEventListener('click', handler);
  }, [target]);
}
