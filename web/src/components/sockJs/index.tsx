'use client';
import React, { useEffect, useState, useCallback, useRef } from 'react';
import SockJS from 'sockjs-client';
import * as StompJS from '@stomp/stompjs';

const SocketJs = () => {
  const client = useRef<StompJS.Client | null>(null);

  const connect = () => {
    client.current = new StompJS.Client({
      // brokerURL: 'ws://10.111.3.121:8080/ws-miseat/ws',
      webSocketFactory: () => new SockJS(`${process.env.DOMAIN}`),
      connectHeaders: {
        login: 'user',
        password: 'password',
      },
      debug: function (err) {
        console.log(err);
      },

      reconnectDelay: 5000, //자동 재 연결
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        subscribe();
      },
      onStompError: (frame) => {
        console.error(frame);
      },
    });

    client.current.activate();
  };

  const disconnect = () => {
    client.current?.deactivate();
  };

  const subscribe = () => {
    client.current?.subscribe(`/sub/3`, (msg: any) => {
      console.log('받은 메세지', msg.body);
    });
  };

  const publish = () => {
    if (!client.current?.connected) {
      return;
    }

    client.current?.publish({
      destination: '/pub/health',
      body: JSON.stringify({ message: 'hi' }),
    });
  };

  useEffect(() => {
    connect();

    return () => disconnect();
  }, []);

  return (
    <>
      <div>
        <div id='menu'>
          <p>Welcome,</p>
        </div>
        <button onClick={() => publish()}>버어튼</button>
      </div>
    </>
  );
};

export default SocketJs;
