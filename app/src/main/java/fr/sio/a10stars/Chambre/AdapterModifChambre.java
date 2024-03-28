package fr.sio.a10stars.Chambre;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Random;

import fr.sio.a10stars.R;

public class AdapterModifChambre extends ArrayAdapter<Chambre> {
    public AdapterModifChambre(Context context, int ressource, List<Chambre> objects) {
        super(context,ressource,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.liste_view_chambre, parent, false);
        }

        TextView numeroChambre = itemView.findViewById(R.id.numeroChambre);
        TextView nb_lit_simple = itemView.findViewById(R.id.nb_lit_simple);
        TextView nb_lit_double = itemView.findViewById(R.id.nb_lit_double);
        TextView statut_chambre = itemView.findViewById(R.id.statut_chambre);
        TextView commentaire = itemView.findViewById(R.id.commentaire);
        Button button = itemView.findViewById(R.id.modificationChambre);
        View pastille_couleur = itemView.findViewById(R.id.pastille_couleur);
        ImageView room = itemView.findViewById(R.id.hotelRoom);

        Chambre currentChambre = getItem(position);
        if (currentChambre != null) {
            numeroChambre.setText(currentChambre.getNum());
            nb_lit_simple.setText(String.valueOf(currentChambre.getNb_lit_simple()));
            nb_lit_double.setText(String.valueOf(currentChambre.getNb_lit_double()));
            statut_chambre.setText(currentChambre.getStatut());
            pastille_couleur.setBackgroundColor(Color.TRANSPARENT);
            Random random = new Random();
            int i = random.nextInt(3) + 1;
            switch (i) {
                case 1:
                    room.setImageResource(R.drawable.hotelroom);
                    break;
                case 2:
                    room.setImageResource(R.drawable.hotelroom2);
                case 3 :
                    room.setImageResource(R.drawable.hotelroom3);
            }
            if(statut_chambre.getText().toString().equals("disponible")) {
                pastille_couleur.setBackgroundColor(itemView.getResources().getColor(R.color.disponible));
            } else if (statut_chambre.getText().toString().equals("occupe")) {
                pastille_couleur.setBackgroundColor(itemView.getResources().getColor(R.color.occupe));
            }else {
                pastille_couleur.setBackgroundColor(itemView.getResources().getColor(R.color.maintenance));
            }
            commentaire.setText(currentChambre.getComm());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChambreForm.class);
                intent.putExtra("ajouter",false);
                Chambre.CurrentIdItem = currentChambre.getId();
                getContext().startActivity(intent);
            }
        });

        return itemView;
    }
}
