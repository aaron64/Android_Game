package com.mygdx.game.util;

import com.mygdx.game.map.MapTheme;

import java.util.Random;

public class MapNameGenerator {
    public static String[] dungeonPre = {"Burrows","Catacombs","Caverns","Cells","Chambers","Crypt","Delves","Dungeon","Grotto","Haunt","Lair","Maze","Pits","Point","Quarters","Tombs","Tunnels","Vault"};
    public static String[] forestPre = {"Forest", "Mountains", "Greens", "Meadows", "Grassland", "Prairie", "Land"};
    public static String[] cloudPre = {"Clouds", "Winds", "Storm", "Forecast"};
    public static String[] adj = {"Abandoned","Abominable","Abomination","Abysmal","Abyss","Adamantine","Adamantite","Ancient","Angry","Arcane","Arching","Arctic","Arid","Barbaric","Bare","Bellowing","Betrayed","Black","Bleak","Blooded","Bloodfall","Bloodlust","Boiling","Bottomless","Brilliant","Broken Bones","Broken Curse","Bronze","Brutal","Buried","Burned","Burning","Chaos","Chaotic","Cobalt","Cold","Collapsing","Conquered","Coral","Courage","Crescent Moon","Cruel","Crying","Crystal","Cunning","Cursed","Damned","Dancing","Dark","Daydream","Dead","Deadly","Death's","Decayed","Decaying","Deep","Deepest","Deepwood","Delusion","Demonic","Depraved","Desert","Deserted","Desolate","Desolated","Destroyed","Destruction","Diamond","Dire","Dishonored","Distant","Doomed","Dragon","Dragonclaw","Dragontooth","Dread","Dreaded","Dreadful","Dream","Dreary","Dry","Dying","Earth","Eastern","Ebon","Eclipse","Elemental","Emerald","Empty","Enchanted","Ender","Enigmatic","Erased","Eternal Agony","Eternal Rest","Eternal","Ethereal","Fabled","Fallen Legion","Fallen","False","Feared","Fearsome","Fire Mountain","Fire","Flowing","Foaming","Forbidden","Forgotten","Forsaken","Fractured","Frozen","Full Moon","Furious","Gentle","Ghost","Glistening","Gloomy","Glowing","Goblin","Gold Mine","Golden","Granite","Grey","Grim","Grizzly","Hallucination","Haunted","Hidden","Hollow","Hopeless","Howling","Hungry","Illusion","Impostor","Infernal","Infinite","Invisible","Iron Mine","Iron","Ironbark","Isolated","Jade","Jagged","Killing","Laughing Skulls","Laughing","Lifeless","Light","Lion Tooth","Living Dead","Living","Lonely","Lost","Lower","Lucent","Lurking Shadow","Mad","Malicious","Mesmerizing","Mighty","Mirage","Mirrored","Misty","Mithril Mine","Mithril","Moaning","Mocking","Molten","Motionless","Mourning","Murky","Mysterious","Mythic","Nameless","Narrow","Neglected","Nether","Neverending","Nightmare","Northern","Obliterated","Oblivion","Obsidian","Ogre","Oracle","Orc","Overhanging","Perished","Phantom","Phoenix","Poisoned","Prisoner","Quiet","Raging","Red","Rejected","Renegade","Restless","Roaring","Rocking","Rugged","Ruthless","Sad","Sanguine","Savage","Scarlet","Scheming","Scorching","Screaming","Secret","Serene","Shadow","Shadowed","Shadowy","Shimmering","Shrieking","Shrouded","Shunned","Silent","Silver","Sleeping","Smoky","Smoldering","Sorrow","Southern","Specter","Spirit","Spirit's","Steel","Sterile","Storm","Sunken","Swamp","Terraced","Thief","Thunder","Thundering","Tormented","Tranquil","Turbulent","Twilight","Twisted","Twisting","Uncanny","Unholy","Unknown","Unspoken","Unstable","Vanished","Vanishing","Vanquished","Vicious","Violent","Voiceless","Volcanic","Wailing","Wasted","Watching Eyes","Western","Whispering","White","Wicked","Wild","Wind","Windy","Winter","Withered","Wondering","Wraith","Wrath","Yawning"};
    public static String[] suffex = {"Arachnid","Army","Basilisk","Bat","Bear","Cult","Desert","Dragon","Eagle","Elf","Emperor","Forest","Giant","Goblin","Guardian","Horsemen","Hound","Hunter","Jungle","King","Knight","Legion","Leopard","Lion","Mage","Marsh","Monk","Morass","Mountain","Occult","Ogre","Oracle","Orc","Paladin","Panther","Phoenix","Priest","Queen","Raven","Scorpion","Serpent","Soldier","Spider","Swamp","Tiger","Warrior","Widow","Witch","Wizard","Warlord","Wolf"};

    public static String generateRandomName(long seed, MapTheme type) {
        Random rand = new Random(seed);

        String name = "";

        String[] pre = null;
        switch(type) {
            case DUNGEON:
                pre = dungeonPre;
                break;
            case FOREST:
                pre = forestPre;
                break;
            case CLOUD:
                pre = cloudPre;
                break;
        }

        name += pre[rand.nextInt(pre.length)];
        name += " of the ";
        name += adj[rand.nextInt(adj.length)];
        name += " ";
        name += suffex[rand.nextInt(suffex.length)];

        return name;
    }
}
