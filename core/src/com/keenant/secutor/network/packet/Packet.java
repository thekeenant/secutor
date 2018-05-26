package com.keenant.secutor.network.packet;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DefaultSerializers.EnumSerializer;
import com.esotericsoftware.kryonet.EndPoint;
import com.keenant.secutor.utils.Direction;
import java.util.ArrayList;
import java.util.UUID;

public interface Packet {
  static void registerPackets(EndPoint endpoint) {
    Kryo kryo = endpoint.getKryo();
    kryo.register(EntityMovePacket.class, 1);
    kryo.register(GladiatorPacket.class, 2);
    kryo.register(JoinPacket.class, 3);
    kryo.register(LeavePacket.class, 4);
    kryo.register(LoginPacket.class, 5);
    kryo.register(WorldSetupPacket.class, 6);

    // Miscellaneous objects...
    {
      kryo.register(ArrayList.class);
      kryo.register(Vector2.class);
      kryo.register(UUID.class, new Serializer<UUID>() {
        @Override
        public void write(Kryo kryo, Output output, UUID object) {
          output.writeString(object.toString());
        }

        @Override
        public UUID read(Kryo kryo, Input input, Class<UUID> type) {
          String uuidStr = input.readString();
          return UUID.fromString(uuidStr);
        }
      });
      kryo.register(Direction.class, new EnumSerializer(Direction.class));
    }
  }
}
